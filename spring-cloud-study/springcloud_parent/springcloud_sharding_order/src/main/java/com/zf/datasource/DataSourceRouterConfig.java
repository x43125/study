package com.zf.datasource;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 数据源路由配置
 * 
 * 功能：
 * 1. 配置读写数据源策略
 * 2. 全局控制数据源路由
 * 3. 支持从配置中心动态刷新（Apollo）
 * 
 * 注意：
 * - 使用 @RefreshScope 使配置可以动态刷新
 * - 使用 @Value 从配置中心读取配置
 * - 配置项可以在 Apollo 配置中心修改并实时生效
 * - 配置项名称：datasource-router.write-mode, datasource-router.read-mode, datasource-router.dual-write-order
 * 
 * @author zf
 * @since 2026-04-12
 */
@Data
@Component
@RefreshScope
public class DataSourceRouterConfig {
    
    /**
     * 写入模式：old/new/both
     * - old: 只写老表
     * - new: 只写新表（分片表）
     * - both: 双写（老表和新表都写）
     * 
     * 配置项：datasource-router.write-mode
     */
    @Value("${datasource-router.write-mode:old}")
    private String writeMode;
    
    /**
     * 读取模式：old/new
     * - old: 只读老表
     * - new: 只读新表（分片表）
     * 
     * 配置项：datasource-router.read-mode
     */
    @Value("${datasource-router.read-mode:old}")
    private String readMode;
    
    /**
     * 双写顺序（当write-mode=both时生效）
     * - old-first: 先写老表，再写新表
     * - new-first: 先写新表，再写老表
     * 
     * 配置项：datasource-router.dual-write-order
     */
    @Value("${datasource-router.dual-write-order:old-first}")
    private String dualWriteOrder;
}
