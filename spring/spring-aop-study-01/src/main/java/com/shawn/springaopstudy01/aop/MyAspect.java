package com.shawn.springaopstudy01.aop;

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
//@Component
public class MyAspect {
    private static final Logger log = LoggerFactory.getLogger(MyAspect.class);

    @Before("execution(* com.shawn.springaopstudy01.service.MyService.foo())")
    public void before() {
        log.info("before()");
    }
}
