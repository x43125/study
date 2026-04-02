package com.zf.sharding;

import com.zf.util.CodeGenerator;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Properties;

/**
 * 订单表分片算法
 * 从 code（雪花算法ID）中提取年月，路由到对应月份的分表
 * 例如：code=xxx 对应 2026年3月 → t_order_202603
 * 如果 code 为 null 或无法解析，路由到 t_order（兜底表）
 */
public class OrderShardingAlgorithm implements StandardShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        Long code = shardingValue.getValue();

        // code 为 null，路由到默认表
        if (code == null) {
            return "t_order";
        }

        try {
            // 从 code 中提取年月
            String yearMonth = CodeGenerator.extractYearMonth(code);
            String targetTable = "t_order_" + yearMonth;

            // 检查目标表是否在可用表列表中
            if (availableTargetNames.contains(targetTable)) {
                return targetTable;
            }

            // 如果目标表不存在，路由到默认表
            return "t_order";
        } catch (Exception e) {
            // 解析失败，路由到默认表
            return "t_order";
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

    }
}
