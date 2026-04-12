package com.zf.sharding;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * 订单按月分片算法
 * 
 * 功能：从雪花算法生成的订单号中提取年月，路由到对应分片表
 * 
 * 算法逻辑：
 * 1. 从order_no中提取时间戳（雪花算法ID右移22位）
 * 2. 将时间戳转换为LocalDate
 * 3. 格式化为年月字符串（yyyyMM）
 * 4. 返回表名：t_order_yyyyMM 或 t_order_item_yyyyMM
 * 
 * 示例：
 * - order_no = 1234567890123456789
 * - 提取时间戳 → 2026-04-12
 * - 格式化 → 202604
 * - 返回表名 → t_order_202604
 * 
 * @author zf
 * @since 2026-04-12
 */
@Slf4j
public class OrderMonthShardingAlgorithm implements StandardShardingAlgorithm<Long> {
    
    /**
     * 雪花算法起始时间戳（2020-01-01 00:00:00 UTC）
     * 可以根据实际系统的雪花算法起始时间调整
     */
    private long snowflakeEpoch = 1577836800000L;
    
    /**
     * 分片表后缀格式（yyyyMM）
     */
    private static final String TABLE_SUFFIX_FORMAT = "%04d%02d";
    
    /**
     * 表名前缀（在配置中指定，这里仅用于日志）
     */
    private String tablePrefix = "t_order";
    
    /**
     * 精确分片（用于 =、IN 查询）
     * 
     * @param availableTargetNames 可用的分片表集合（如：t_order_202510, t_order_202511, ...）
     * @param shardingValue 分片值（包含列名和值）
     * @return 目标分片表名
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, 
                            PreciseShardingValue<Long> shardingValue) {
        Long orderNo = shardingValue.getValue();
        String tableName = shardingValue.getLogicTableName();
        
        log.debug("精确分片 - orderNo: {}, availableTables: {}, logicTable: {}", 
                  orderNo, availableTargetNames, tableName);
        
        // 从订单号中提取年月
        String suffix = extractMonthFromSnowflakeId(orderNo);
        
        // 构建目标表名
        String targetTableName = tableName + "_" + suffix;
        
        // 检查目标表是否存在
        if (availableTargetNames.contains(targetTableName)) {
            log.info("路由到分片表: {}", targetTableName);
            return targetTableName;
        } else {
            log.error("目标分片表不存在: {}, 可用表: {}", targetTableName, availableTargetNames);
            throw new IllegalArgumentException("目标分片表不存在: " + targetTableName);
        }
    }
    
    /**
     * 范围分片（用于 BETWEEN、>、<、>=、<= 查询）
     * 
     * @param availableTargetNames 可用的分片表集合
     * @param shardingValue 分片值范围
     * @return 目标分片表名集合
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, 
                                        RangeShardingValue<Long> shardingValue) {
        String tableName = shardingValue.getLogicTableName();
        final String[] lowerSuffix = {null};
        final String[] upperSuffix = {null};
        
        log.debug("范围分片 - availableTables: {}, logicTable: {}", 
                  availableTargetNames, tableName);
        
        // 提取下界值
        if (shardingValue.getValueRange().hasLowerBound()) {
            Long lowerOrderNo = shardingValue.getValueRange().lowerEndpoint();
            lowerSuffix[0] = extractMonthFromSnowflakeId(lowerOrderNo);
            log.debug("下界 - orderNo: {}, suffix: {}", lowerOrderNo, lowerSuffix[0]);
        }
        
        // 提取上界值
        if (shardingValue.getValueRange().hasUpperBound()) {
            Long upperOrderNo = shardingValue.getValueRange().upperEndpoint();
            upperSuffix[0] = extractMonthFromSnowflakeId(upperOrderNo);
            log.debug("上界 - orderNo: {}, suffix: {}", upperOrderNo, upperSuffix[0]);
        }
        
        // 筛选在范围内的分片表
        return availableTargetNames.stream()
                .filter(table -> {
                    String tableSuffix = table.substring(table.lastIndexOf("_") + 1);
                    
                    // 如果没有下界，检查是否 <= 上界
                    if (lowerSuffix[0] == null) {
                        return tableSuffix.compareTo(upperSuffix[0]) <= 0;
                    }
                    
                    // 如果没有上界，检查是否 >= 下界
                    if (upperSuffix[0] == null) {
                        return tableSuffix.compareTo(lowerSuffix[0]) >= 0;
                    }
                    
                    // 如果有下界和上界，检查是否在范围内
                    return tableSuffix.compareTo(lowerSuffix[0]) >= 0 
                            && tableSuffix.compareTo(upperSuffix[0]) <= 0;
                })
                .collect(Collectors.toList());
    }
    
    /**
     * 从雪花算法ID中提取年月
     * 
     * @param snowflakeId 雪花算法ID
     * @return 年月字符串（格式：yyyyMM）
     */
    private String extractMonthFromSnowflakeId(Long snowflakeId) {
        try {
            // 雪花算法结构：1位符号位 + 41位时间戳 + 10位机器ID + 12位序列号
            // 右移22位，去掉机器ID和序列号
            long timestamp = snowflakeId >> 22;
            
            // 加上起始时间戳，得到实际时间戳（毫秒）
            long actualTimestamp = timestamp + snowflakeEpoch;
            
            // 转换为LocalDate
            LocalDate date = new Date(actualTimestamp).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            
            // 格式化为年月字符串
            String suffix = String.format(TABLE_SUFFIX_FORMAT, 
                                          date.getYear(), 
                                          date.getMonthValue());
            
            log.debug("雪花ID: {} → 时间戳: {} → 日期: {} → 分片后缀: {}", 
                     snowflakeId, actualTimestamp, date, suffix);
            
            return suffix;
            
        } catch (Exception e) {
            log.error("从雪花ID提取年月失败: {}", snowflakeId, e);
            throw new RuntimeException("从雪花ID提取年月失败", e);
        }
    }
    
    /**
     * 初始化算法（读取配置）
     * 
     * @param props 配置属性
     */
    @Override
    public void init(Properties props) {
        // 可以从配置中读取自定义参数
        // 例如：雪花算法起始时间戳
        if (props != null) {
            String epoch = props.getProperty("snowflake-epoch");
            if (epoch != null && !epoch.isEmpty()) {
                snowflakeEpoch = Long.parseLong(epoch);
                log.info("使用自定义雪花算法起始时间戳: {}", snowflakeEpoch);
            }
            
            String prefix = props.getProperty("table-prefix");
            if (prefix != null && !prefix.isEmpty()) {
                this.tablePrefix = prefix;
                log.info("使用表名前缀: {}", tablePrefix);
            }
        }
        
        log.info("OrderMonthShardingAlgorithm初始化完成");
    }
    
    /**
     * 获取算法类型
     * 
     * @return 算法类型
     */
    @Override
    public String getType() {
        return "ORDER_MONTH_SHARDING";
    }
    
    /**
     * 获取配置属性
     * 
     * @return 配置属性
     */
    @Override
    public Properties getProps() {
        return new Properties();
    }
}
