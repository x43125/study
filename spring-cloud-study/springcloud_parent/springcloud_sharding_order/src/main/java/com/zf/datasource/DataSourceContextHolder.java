package com.zf.datasource;

/**
 * 数据源上下文
 * 
 * 功能：使用ThreadLocal保存当前线程使用的数据源key
 * 
 * @author zf
 * @since 2026-04-12
 */
public class DataSourceContextHolder {
    
    /**
     * 数据源key常量
     */
    public static final String OLD_DATASOURCE = "oldDataSource";
    public static final String NEW_DATASOURCE = "newDataSource";
    
    /**
     * 使用ThreadLocal保存当前线程的数据源key
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();
    
    /**
     * 设置当前线程的数据源key
     * 
     * @param dataSourceKey 数据源key
     */
    public static void setDataSourceKey(String dataSourceKey) {
        CONTEXT_HOLDER.set(dataSourceKey);
    }
    
    /**
     * 获取当前线程的数据源key
     * 
     * @return 数据源key
     */
    public static String getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }
    
    /**
     * 清除当前线程的数据源key
     */
    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }
}