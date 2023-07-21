package com.shawn.springaopstudy01.proxy.jdk.principal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理，需要实现接口
 * @author wangxiang
 * @date 2023/6/29 09:07
 * @description
 */
public class JdkProxyDemo {

    interface Foo {
        void foo();
    }

    static class Target implements Foo {

        /**
         * 将被增强的方法
         */
        @Override
        public void foo() {
            System.out.println("target foo");
        }
    }

    public static void main(String[] args) {
        Target target = new Target();

        ClassLoader classLoader = JdkProxyDemo.class.getClassLoader();
        Foo fooProxy = (Foo) Proxy.newProxyInstance(classLoader, new Class[]{Foo.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 前置增强
                System.out.println("before...");
                // 正常调用：object.method(args)
                // 反射调用：method.invoke(object, args)
                Object invoke = method.invoke(target, args);
                // 后置增强
                System.out.println("after...");
                return invoke;
            }
        });

        fooProxy.foo();
    }
}
