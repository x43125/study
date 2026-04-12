package com.zf.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 数据源路由配置
 * 
 * 功能：
 * 1. 配置读写数据源策略
 * 2. 全局控制数据源路由
 * 
 * 注意：修改配置后需要重启应用才能生效
 * 
 * @author zf
 * @since 2026-04-12
 */
@Data
@Component
@ConfigurationProperties(prefix = "datasource-router")
public class DataSourceRouterConfig {
    
    /**
     * 写入模式：old/new/both
     * - old: 只写老表
     * - new: 只写新表（分片表）
     * - both: 双写（老表和新表都写）
     */
    private String writeMode = "old";
    
    /**
     * 读取模式：old/new
     * - old: 只读老表
     * - new: 只读新表（分片表）
     */
    private String readMode = "old";
    
    /**
     * 双写顺序（当write-mode=both时生效）
     * - old-first: 先写老表，再写新表
     * - new-first: 先写新表，再写老表
     */
    private String dualWriteOrder = "old-first";
}