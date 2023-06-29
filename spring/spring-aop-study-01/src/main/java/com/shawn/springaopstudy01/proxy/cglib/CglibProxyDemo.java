package com.shawn.springaopstudy01.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wangxiang
 * @date 2023/6/29 09:20
 * @description
 */
public class CglibProxyDemo {
    static class Target {
        public void foo() {
            System.out.println("Target foo");
        }
    }

    public static void main(String[] args) {
        Target target = new Target();
        Target targetProxy = (Target) Enhancer.create(Target.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before...");
                Object invoke = method.invoke(target, args);
                System.out.println("after...");
                return invoke;
            }
        });

        targetProxy.foo();
    }
}
