package com.zf.datasource;

import java.lang.annotation.*;

/**
 * 数据源路由注解
 * 
 * 功能：通过注解指定使用哪个数据源
 * 
 * 使用示例：
 * <pre>
 * &#64;DataSourceRouter(value = DataSourceContextHolder.OLD_DATASOURCE)
 * public void writeToOldTable() {
 *     // 写入老表
 * }
 * 
 * &#64;DataSourceRouter(value = DataSourceContextHolder.NEW_DATASOURCE)
 * public void writeToNewTable() {
 *     // 写入新表（分片表）
 * }
 * 
 * &#64;DataSourceRouter(value = DataSourceContextHolder.OLD_DATASOURCE, read = true)
 * public Order readFromOldTable(Long orderNo) {
 *     // 从老表读取
 * }
 * 
 * &#64;DataSourceRouter(value = DataSourceContextHolder.NEW_DATASOURCE, read = true)
 * public Order readFromNewTable(Long orderNo) {
 *     // 从新表读取（分片表）
 * }
 * </pre>
 * 
 * @author zf
 * @since 2026-04-12
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceRouter {
    
    /**
     * 数据源key
     * 
     * @return 数据源key
     */
    String value() default DataSourceContextHolder.OLD_DATASOURCE;
    
    /**
     * 是否只读
     * 
     * @return true-只读，false-可读写
     */
    boolean read() default false;
    
    /**
     * 是否同时写入老表和新表（双写）
     * 
     * @return true-双写，false-单写
     */
    boolean dualWrite() default false;
}