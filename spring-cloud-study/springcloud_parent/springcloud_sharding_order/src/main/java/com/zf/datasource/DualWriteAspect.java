package com.zf.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 双写AOP切面
 * 
 * 功能：
 * 1. 拦截@DataSourceRouter(dualWrite=true)注解
 * 2. 同时写入老表和新表
 * 3. 处理双写失败的情况
 * 4. 支持配置双写策略
 * 
 * @author zf
 * @since 2026-04-12
 */
@Slf4j
@Aspect
@Component
@Order(2) // 在DataSourceAspect之后执行
public class DualWriteAspect {
    
    /**
     * 最大重试次数
     */
    private static final int MAX_RETRY_COUNT = 3;
    
    /**
     * 定义切点：拦截带有@DataSourceRouter注解且dualWrite=true的方法
     */
    @Pointcut("@annotation(com.zf.datasource.DataSourceRouter) && @annotation(dataSourceRouter) && @annotation(dataSourceRouter) && if()")
    public void dualWritePointcut(DataSourceRouter dataSourceRouter) {
        // 这里只是定义切点，实际判断在around方法中
    }
    
    /**
     * 环绕通知：实现双写逻辑
     * 
     * @param joinPoint 连接点
     * @return 方法执行结果
     * @throws Throwable 异常
     */
    @Around("@annotation(dataSourceRouter)")
    public Object around(ProceedingJoinPoint joinPoint, DataSourceRouter dataSourceRouter) throws Throwable {
        // 如果不是双写模式，直接执行
        if (!dataSourceRouter.dualWrite()) {
            return joinPoint.proceed();
        }
        
        // 双写模式
        log.info("双写模式开始执行 - method: {}", joinPoint.getSignature().getName());
        
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        
        // 第一次写入：使用指定的数据源（默认老表）
        Object result = null;
        Exception oldException = null;
        Exception newException = null;
        
        // 写入老表
        try {
            DataSourceContextHolder.setDataSourceKey(DataSourceContextHolder.OLD_DATASOURCE);
            result = joinPoint.proceed();
            log.info("老表写入成功 - method: {}", method.getName());
        } catch (Exception e) {
            oldException = e;
            log.error("老表写入失败 - method: {}", method.getName(), e);
        } finally {
            DataSourceContextHolder.clearDataSourceKey();
        }
        
        // 写入新表（分片表）
        try {
            DataSourceContextHolder.setDataSourceKey(DataSourceContextHolder.NEW_DATASOURCE);
            // 重新执行方法，写入新表
            joinPoint.proceed();
            log.info("新表写入成功 - method: {}", method.getName());
        } catch (Exception e) {
            newException = e;
            log.error("新表写入失败 - method: {}", method.getName(), e);
        } finally {
            DataSourceContextHolder.clearDataSourceKey();
        }
        
        // 处理双写结果
        if (oldException != null && newException != null) {
            // 双写都失败，抛出异常
            log.error("双写都失败 - method: {}", method.getName());
            throw new RuntimeException("双写失败：老表和新表都写入失败", newException);
        } else if (oldException != null) {
            // 老表写入失败，记录日志但不抛出异常（根据业务需求调整）
            log.warn("老表写入失败，新表写入成功 - method: {}", method.getName());
        } else if (newException != null) {
            // 新表写入失败，记录日志但不抛出异常（根据业务需求调整）
            log.warn("新表写入失败，老表写入成功 - method: {}", method.getName());
        } else {
            // 双写都成功
            log.info("双写都成功 - method: {}", method.getName());
        }
        
        return result;
    }
    
    /**
     * 实现条件判断的静态方法
     * 
     * @param joinPoint 连接点
     * @param dataSourceRouter 数据源路由注解
     * @return 是否启用双写
     */
    public static boolean if_() {
        // 这个方法只是为了让Pointcut编译通过
        // 实际判断在around方法中
        return true;
    }
}