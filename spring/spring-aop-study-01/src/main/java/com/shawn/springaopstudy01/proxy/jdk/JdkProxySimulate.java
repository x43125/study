package com.shawn.springaopstudy01.proxy.jdk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wangxiang
 * @date 2023/6/29 10:00
 * @description
 */
public class JdkProxySimulate {

    /**
     * 待实现的接口
     */
    interface Foo {
        /**
         * 待增强的方法
         */
        void foo();

        int bar();
    }

    static class Target implements Foo {

        @Override
        public void foo() {
            System.out.println("foo()");
        }

        @Override
        public int bar() {
            System.out.println("bar()");
            return 100;
        }
    }

    interface InvocationHandler {
        Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
    }

    public static void main(String[] args) {
        Foo target = new $Proxy0(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
                // 1. 功能增强 这部分的代码实际的时候不可能像这样写死在这里，所以需要抽出来
                //  最终就抽到此处
                System.out.println("before...");
                // 2. 调用目标 此处直接这样写，则无论接口有多少个方法都会被增强成这一个，也不符合实际要求，遂需要修改
//                new Target().foo();
                return method.invoke(new Target(), args);
            }
        });
        target.foo();
        target.bar();
    }
}
