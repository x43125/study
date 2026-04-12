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
 * 数据源AOP切面
 * 
 * 功能：
 * 1. 拦截@DataSourceRouter注解
 * 2. 方法执行前切换数据源
 * 3. 方法执行后清除数据源上下文
 * 
 * @author zf
 * @since 2026-04-12
 */
@Slf4j
@Aspect
@Component
@Order(1) // 确保在事务切面之前执行
public class DataSourceAspect {
    
    /**
     * 定义切点：拦截带有@DataSourceRouter注解的方法
     */
    @Pointcut("@annotation(com.zf.datasource.DataSourceRouter)")
    public void dataSourcePointcut() {
    }
    
    /**
     * 环绕通知：在方法执行前后处理数据源切换
     * 
     * @param joinPoint 连接点
     * @return 方法执行结果
     * @throws Throwable 异常
     */
    @Around("dataSourcePointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        
        // 获取注解
        DataSourceRouter dataSourceRouter = method.getAnnotation(DataSourceRouter.class);
        if (dataSourceRouter == null) {
            // 如果方法上没有注解，尝试从类上获取
            dataSourceRouter = joinPoint.getTarget().getClass().getAnnotation(DataSourceRouter.class);
        }
        
        if (dataSourceRouter != null) {
            // 获取数据源key
            String dataSourceKey = dataSourceRouter.value();
            
            // 设置数据源
            DataSourceContextHolder.setDataSourceKey(dataSourceKey);
            
            try {
                // 执行方法
                Object result = joinPoint.proceed();
                
                return result;
            } finally {
                // 清除数据源上下文
                DataSourceContextHolder.clearDataSourceKey();
            }
        }
        
        // 如果没有注解，直接执行方法
        return joinPoint.proceed();
    }
}