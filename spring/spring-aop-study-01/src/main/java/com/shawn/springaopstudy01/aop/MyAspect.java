package com.shawn.springaopstudy01.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author wangxiang
 * @date 2023/6/28 22:51
 * @description
 */
@Aspect
@Component
public class MyAspect {
    private static final Logger log = LoggerFactory.getLogger(MyAspect.class);

    @Before("execution(* com.shawn.springaopstudy01.service.MyService.foo())")
    public void before() {
        log.info("before()");
    }

    @Around("execution(* com.shawn.springaopstudy01.service.MyService.foo())")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 在目标方法执行之前执行的逻辑
        System.out.println("Before executing the method: " + joinPoint.getSignature().getName());

        // 执行目标方法
//        Object result = joinPoint.proceed();
        joinPoint.proceed();

        // 在目标方法执行之后执行的逻辑
        System.out.println("After executing the method: " + joinPoint.getSignature().getName());

        // 可以修改目标方法的返回值，如果不需要修改，直接返回result即可
        // result = someModificationLogic(result);

//        return result;
    }
}
