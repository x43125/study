package com.shawn.springstudy.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * jdk动态代理，针对接口的 定义切面
 *
 * @author wangxiang
 * @date 2023/6/24 17:04
 * @description
 */
@EnableAspectJAutoProxy
@Component
@Aspect
public class LogAspectAnnotationCglib {

    /**
     * 定义切入点
     * 定义到下一级，就可以命中 CglibProxyDemoServiceImpl，定义到上一级就不行
     */
    // 不可以
//    @Pointcut("execution(* com.shawn.springstudy.service.*.*(..))")
    // 可以
    @Pointcut("execution(* com.shawn.springstudy.service.impl.*.*(..))")
    public void pointCutMethod() {

    }

    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("----------------------");
        System.out.println("环绕通知：进入方法");
        Object o = pjp.proceed();
        System.out.println("环绕通知：退出方法");
        return o;
    }

    /**
     * 前置通知
     */
    @Before("pointCutMethod()")
    public void doBefore() {
        System.out.println("前置通知");
    }

    /**
     * 后置通知
     *
     * @param result
     */
    @AfterReturning(pointcut = "pointCutMethod()", returning = "result")
    public void doAfterReturning(String result) {
        System.out.println("后置通知，返回值：" + result);
    }

    /**
     * 异常通知.
     *
     * @param e exception
     */
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        System.out.println("异常通知, 异常: " + e.getMessage());
    }

    /**
     * 最终通知.
     */
    @After("pointCutMethod()")
    public void doAfter() {
        System.out.println("最终通知");
    }
}
