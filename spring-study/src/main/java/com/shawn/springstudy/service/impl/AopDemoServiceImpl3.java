package com.shawn.springstudy.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 目标类
 *
 * @author wangxiang
 * @date 2023/6/21 20:30
 * @description
 */
@Service
public class AopDemoServiceImpl3 {
    public void doMethod1() {
        System.out.println("AopDemoServiceImpl2.doMethod1");
    }

    public String doMethod2() {
        System.out.println("AopDemoServiceImpl2.doMethod2");
        return "hello world";
    }

    public void doMethod3() throws Exception {
        System.out.println("AopDemoServiceImpl2.doMethod3");
        throw new Exception("some exception");
    }
}
