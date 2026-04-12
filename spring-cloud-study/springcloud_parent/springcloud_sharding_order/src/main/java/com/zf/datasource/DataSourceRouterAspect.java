package com.zf.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据源路由AOP切面
 * 
 * 功能：
 * 1. 自动拦截Service层方法
 * 2. 根据配置自动路由数据源
 * 3. 支持双写模式
 * 4. 根据方法名自动判断读/写操作
 * 
 * @author zf
 * @since 2026-04-12
 */
@Slf4j
@Aspect
@Component
public class DataSourceRouterAspect {
    
    @Autowired
    private DataSourceRouterConfig config;
    
    /**
     * 环绕通知：拦截Service层方法
     * 
     * @param joinPoint 连接点
     * @return 方法执行结果
     * @throws Throwable 异常
     */
    @Around("execution(* com.zf.service..*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        
        // 判断是读操作还是写操作
        boolean isRead = isReadMethod(methodName);
        
        log.info("数据源路由 - method: {}, isRead: {}, writeMode: {}, readMode: {}", 
                methodName, isRead, config.getWriteMode(), config.getReadMode());
        
        if (isRead) {
            // 读操作
            return handleReadOperation(joinPoint, method);
        } else {
            // 写操作
            return handleWriteOperation(joinPoint, method);
        }
    }
    
    /**
     * 判断是否是读方法
     * 
     * @param methodName 方法名
     * @return true-读方法，false-写方法
     */
    private boolean isReadMethod(String methodName) {
        String lowerName = methodName.toLowerCase();
        return lowerName.startsWith("get") 
                || lowerName.startsWith("select") 
                || lowerName.startsWith("query") 
                || lowerName.startsWith("find")
                || lowerName.startsWith("list")
                || lowerName.startsWith("count");
    }
    
    /**
     * 处理读操作
     * 
     * @param joinPoint 连接点
     * @param method 方法
     * @return 方法执行结果
     * @throws Throwable 异常
     */
    private Object handleReadOperation(ProceedingJoinPoint joinPoint, Method method) throws Throwable {
        String dataSourceKey = "old".equals(config.getReadMode()) 
                ? DataSourceContextHolder.OLD_DATASOURCE 
                : DataSourceContextHolder.NEW_DATASOURCE;
        
        log.info("读操作路由 - method: {}, dataSource: {}", method.getName(), dataSourceKey);
        
        DataSourceContextHolder.setDataSourceKey(dataSourceKey);
        try {
            return joinPoint.proceed();
        } finally {
            DataSourceContextHolder.clearDataSourceKey();
        }
    }
    
    /**
     * 处理写操作
     * 
     * @param joinPoint 连接点
     * @param method 方法
     * @return 方法执行结果
     * @throws Throwable 异常
     */
    private Object handleWriteOperation(ProceedingJoinPoint joinPoint, Method method) throws Throwable {
        String writeMode = config.getWriteMode();
        
        if ("both".equals(writeMode)) {
            // 双写模式
            return handleDualWrite(joinPoint, method);
        } else if ("old".equals(writeMode)) {
            // 只写老表
            return handleSingleWrite(joinPoint, method, DataSourceContextHolder.OLD_DATASOURCE);
        } else {
            // 只写新表（分片表）
            return handleSingleWrite(joinPoint, method, DataSourceContextHolder.NEW_DATASOURCE);
        }
    }
    
    /**
     * 处理双写操作
     * 
     * @param joinPoint 连接点
     * @param method 方法
     * @return 方法执行结果
     * @throws Throwable 异常
     */
    private Object handleDualWrite(ProceedingJoinPoint joinPoint, Method method) throws Throwable {
        String dualWriteOrder = config.getDualWriteOrder();
        Object result = null;
        Exception firstException = null;
        Exception secondException = null;
        
        log.info("双写模式开始 - method: {}, order: {}", method.getName(), dualWriteOrder);
        
        if ("old-first".equals(dualWriteOrder)) {
            // 先写老表，再写新表
            try {
                result = handleSingleWrite(joinPoint, method, DataSourceContextHolder.OLD_DATASOURCE);
                log.info("双写：老表写入成功 - method: {}", method.getName());
            } catch (Exception e) {
                firstException = e;
                log.error("双写：老表写入失败 - method: {}", method.getName(), e);
            }
            
            try {
                handleSingleWrite(joinPoint, method, DataSourceContextHolder.NEW_DATASOURCE);
                log.info("双写：新表写入成功 - method: {}", method.getName());
            } catch (Exception e) {
                secondException = e;
                log.error("双写：新表写入失败 - method: {}", method.getName(), e);
            }
        } else {
            // 先写新表，再写老表
            try {
                result = handleSingleWrite(joinPoint, method, DataSourceContextHolder.NEW_DATASOURCE);
                log.info("双写：新表写入成功 - method: {}", method.getName());
            } catch (Exception e) {
                firstException = e;
                log.error("双写：新表写入失败 - method: {}", method.getName(), e);
            }
            
            try {
                handleSingleWrite(joinPoint, method, DataSourceContextHolder.OLD_DATASOURCE);
                log.info("双写：老表写入成功 - method: {}", method.getName());
            } catch (Exception e) {
                secondException = e;
                log.error("双写：老表写入失败 - method: {}", method.getName(), e);
            }
        }
        
        // 处理双写结果
        if (firstException != null && secondException != null) {
            // 双写都失败，抛出异常
            log.error("双写都失败 - method: {}", method.getName());
            throw new RuntimeException("双写失败：老表和新表都写入失败", firstException);
        } else if (firstException != null) {
            // 第一次写入失败，第二次成功
            log.warn("双写部分成功 - method: {}", method.getName());
        } else if (secondException != null) {
            // 第一次成功，第二次失败
            log.warn("双写部分成功 - method: {}", method.getName());
        } else {
            // 双写都成功
            log.info("双写都成功 - method: {}", method.getName());
        }
        
        return result;
    }
    
    /**
     * 处理单写操作
     * 
     * @param joinPoint 连接点
     * @param method 方法
     * @param dataSourceKey 数据源key
     * @return 方法执行结果
     * @throws Throwable 异常
     */
    private Object handleSingleWrite(ProceedingJoinPoint joinPoint, Method method, String dataSourceKey) throws Throwable {
        log.info("单写模式 - method: {}, dataSource: {}", method.getName(), dataSourceKey);
        
        DataSourceContextHolder.setDataSourceKey(dataSourceKey);
        try {
            return joinPoint.proceed();
        } finally {
            DataSourceContextHolder.clearDataSourceKey();
        }
    }
}