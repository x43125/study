package com.zf.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源
 * 
 * 功能：继承AbstractRoutingDataSource，实现动态数据源切换
 * 
 * @author zf
 * @since 2026-04-12
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    
    /**
     * 构造函数
     * 
     * @param targetDataSources 目标数据源Map
     * @param defaultTargetDataSource 默认数据源
     */
    public DynamicDataSource(Map<Object, Object> targetDataSources, Object defaultTargetDataSource) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
    }
    
    /**
     * 确定当前使用的数据源key
     * 
     * @return 数据源key
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceKey();
    }
}