package com.shawn.springstudy.service.impl;

import com.shawn.springstudy.service.IJdkProxyService;
import org.springframework.stereotype.Component;

/**
 * 实现类
 * @author wangxiang
 * @date 2023/6/24 17:02
 * @description
 */
@Component
public class JdkProxyDemoServiceImpl implements IJdkProxyService {
    @Override
    public void doMethod1() {
        System.out.println("JdkProxyServiceImpl.doMethod1()");
    }

    @Override
    public String doMethod2() {
        System.out.println("JdkProxyServiceImpl.doMethod2()");
        return "hello world";
    }

    @Override
    public String doMethod3() throws Exception {
        System.out.println("JdkProxyServiceImpl.doMethod3()");
        throw new Exception("some exception");
    }
}
