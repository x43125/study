package com.shawn.springaopstudy01.aop;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangxiang
 * @date 2023/6/28 22:51
 * @description
 */
@Aspect
public class MyAspect {
    private static final Logger log = LoggerFactory.getLogger(MyAspect.class);

    public void before() {
        
    }
}
