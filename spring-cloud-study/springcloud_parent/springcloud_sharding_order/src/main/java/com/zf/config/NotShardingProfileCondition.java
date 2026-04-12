package com.zf.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;

/**
 * 非Sharding Profile条件
 * 
 * 当spring.profiles.active不等于sharding时，此条件返回true
 * 用于在非Sharding模式下启用DataSourceConfig
 * 
 * @author zf
 * @since 2026-04-12
 */
public class NotShardingProfileCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String profiles = context.getEnvironment().getProperty("spring.profiles.active");
        if (profiles != null && !profiles.isEmpty()) {
            String[] profileArray = profiles.split(",");
            return !Arrays.asList(profileArray).contains("sharding");
        }
        
        return true; // 默认返回true，在未指定profile时使用DataSourceConfig
    }
}
