package com.shawn.springstudy.service;

/**
 * 目标类
 *
 * @author wangxiang
 * @date 2023/6/21 20:30
 * @description
 */
public class AopDemoServiceImpl {
    public void doMethod1() {
        System.out.println("AopDemoServiceImpl.doMethod1");
    }

    public String doMethod2() {
        System.out.println("AopDemoServiceImpl.doMethod2");
        return "hello world";
    }

    public void doMethod3() throws Exception {
        System.out.println("AopDemoServiceImpl.doMethod3");
        throw new Exception("some exception");
    }
}
