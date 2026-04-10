package com.zf.sharding;

import com.zf.util.CodeGenerator;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Properties;

/**
 * 订单表分片算法（兼容老数据和新数据）
 * 分片键：code
 * 路由规则：
 *   1. code 为 null → t_order（兜底表）
 *   2. 老格式（ORD + 时间戳 + 随机数）→ 提取年月 → t_order_YYYYMM
 *   3. 雪花算法格式（新数据）→ 提取年月 → t_order_YYYYMM
 *   4. 未知格式 → t_order（兜底表）
 * 
 * 老数据示例：ORD17743643638409224
 *   - 前缀："ORD"（3位）
 *   - 时间戳：1774364363840（13位，毫秒）
 *   - 随机数：9224（4位）
 *   - 提取年月：202604 → t_order_202604
 * 
 * 新数据示例：1234567890123456789（雪花算法）
 *   - 提取年月：202604 → t_order_202604
 */
public class OrderShardingAlgorithm implements StandardShardingAlgorithm<Long> {
    
    /**
     * 雪花算法最小ID（2024-01-01）
     */
    private static final long MIN_SNOWFLAKE_ID = 1790164589563080704L;
    
    /**
     * 老code前缀
     */
    private static final String LEGACY_CODE_PREFIX = "ORD";
    
    /**
     * 老code最小长度（ORD + 13位时间戳 + 4位随机数 = 20位）
     */
    private static final int MIN_LEGACY_CODE_LENGTH = 20;
    
    /**
     * 老code最大长度（ORD + 13位时间戳 + 4位随机数 = 20位）
     */
    private static final int MAX_LEGACY_CODE_LENGTH = 20;
    
    /**
     * 老code最小时间戳（2020-01-01）
     */
    private static final long MIN_LEGACY_TIMESTAMP = 1577836800000L;
    
    /**
     * 老code最大时间戳（2030-12-31）
     */
    private static final long MAX_LEGACY_TIMESTAMP = 1924902400000L;
    
    /**
     * 日期格式化器
     */
    private static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");
    
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        Long code = shardingValue.getValue();
        
        if (code == null) {
            System.out.println("code 为 null，路由到兜底表: t_order");
            return "t_order";
        }
        
        // 尝试按老格式路由
        String targetTable = routeByLegacy(code, availableTargetNames);
        if (targetTable != null) {
            return targetTable;
        }
        
        // 尝试按雪花算法路由
        targetTable = routeBySnowflake(code, availableTargetNames);
        if (targetTable != null) {
            return targetTable;
        }
        
        // 都无法识别，兜底
        System.out.println("code=" + code + " 无法识别格式，路由到兜底表: t_order");
        return "t_order";
    }
    
    /**
     * 按老格式路由（ORD + 时间戳 + 随机数）
     * 
     * 示例：ORD17743643638409224
     *   1. 转换为字符串
     *   2. 验证格式（前缀为"ORD"，长度为20位）
     *   3. 提取时间戳（去掉前缀3位，取前13位）
     *   4. 转换为年月
     *   5. 路由到 t_order_YYYYMM
     */
    private String routeByLegacy(Long code, Collection<String> availableTargetNames) {
        try {
            String codeStr = String.valueOf(code);
            
            // 1. 检查长度
            if (codeStr.length() < MIN_LEGACY_CODE_LENGTH || 
                codeStr.length() > MAX_LEGACY_CODE_LENGTH) {
                return null;
            }
            
            // 2. 检查前缀
            if (!codeStr.startsWith(LEGACY_CODE_PREFIX)) {
                return null;
            }
            
            // 3. 提取时间戳（去掉前缀3位，取前13位）
            // ORD17743643638409224 → 17743643638409224 → 1774364363840
            String codeWithoutPrefix = codeStr.substring(3); // "17743643638409224"
            
            // 取前13位作为时间戳（后4位是随机数）
            long timestamp;
            if (codeWithoutPrefix.length() >= 13) {
                timestamp = Long.parseLong(codeWithoutPrefix.substring(0, 13));
            } else {
                return null;
            }
            
            // 4. 验证时间戳范围（2020-2030）
            if (timestamp < MIN_LEGACY_TIMESTAMP || timestamp > MAX_LEGACY_TIMESTAMP) {
                return null;
            }
            
            // 5. 转换为年月
            LocalDateTime dateTime = LocalDateTime.ofEpochSecond(
                timestamp / 1000,
                (int)(timestamp % 1000) * 1000000,
                ZoneOffset.UTC
            );
            String yearMonth = dateTime.format(YEAR_MONTH_FORMATTER);
            
            // 6. 构造目标表名
            String targetTable = "t_order_" + yearMonth;
            
            // 7. 检查目标表是否存在
            if (availableTargetNames != null && !availableTargetNames.isEmpty()) {
                if (availableTargetNames.contains(targetTable)) {
                    System.out.println("code=" + code + "（老格式），路由到: " + targetTable);
                    return targetTable;
                }
                // 目标表不存在，兜底
                System.out.println("code=" + code + "（老格式），目标表 " + targetTable + " 不存在，路由到兜底表");
                return "t_order";
            }
            
            // 没有提供可用表列表，直接返回目标表名
            System.out.println("code=" + code + "（老格式），路由到: " + targetTable);
            return targetTable;
            
        } catch (Exception e) {
            System.err.println("老格式解析失败: code=" + code + ", error=" + e.getMessage());
            return null;
        }
    }
    
    /**
     * 按雪花算法路由
     * 
     * 示例：1234567890123456789
     *   1. 检查是否为雪花算法格式（>= 1790164589563080704L）
     *   2. 使用 CodeGenerator.extractYearMonth() 提取年月
     *   3. 路由到 t_order_YYYYMM
     */
    private String routeBySnowflake(Long code, Collection<String> availableTargetNames) {
        try {
            // 1. 检查是否符合雪花算法格式
            if (code < MIN_SNOWFLAKE_ID) {
                return null;
            }
            
            // 2. 提取年月
            String yearMonth = CodeGenerator.extractYearMonth(code);
            
            // 3. 构造目标表名
            String targetTable = "t_order_" + yearMonth;
            
            // 4. 检查目标表是否存在
            if (availableTargetNames != null && !availableTargetNames.isEmpty()) {
                if (availableTargetNames.contains(targetTable)) {
                    System.out.println("code=" + code + "（雪花算法），路由到: " + targetTable);
                    return targetTable;
                }
                // 目标表不存在，兜底
                System.out.println("code=" + code + "（雪花算法），目标表 " + targetTable + " 不存在，路由到兜底表");
                return "t_order";
            }
            
            // 没有提供可用表列表，直接返回目标表名
            System.out.println("code=" + code + "（雪花算法），路由到: " + targetTable);
            return targetTable;
            
        } catch (Exception e) {
            System.err.println("雪花算法解析失败: code=" + code + ", error=" + e.getMessage());
            return null;
        }
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        // 范围查询：返回所有可能的表
        return new LinkedHashSet<>(availableTargetNames);
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void init(Properties properties) {
        System.out.println("==============================================");
        System.out.println("订单表分片算法初始化成功");
        System.out.println("兼容老格式：ORD + 时间戳 + 随机数");
        System.out.println("兼容新格式：雪花算法ID");
        System.out.println("分片规则：按月分表（t_order_YYYYMM）");
        System.out.println("兜底表：t_order");
        System.out.println("==============================================");
    }
}