package com.wx.base.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Shawn
 * @date 2025/5/23 09:01
 * @description
 */
public class Reflection01 {
    public static void main(String[] args) {
        // JDK Proxy方式动态代理
        // 源对象
        Hello hello = new HelloImpl();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(hello);
        // 以接口为纽带
        // 增强对象
        Hello helloProxy = (Hello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(), HelloImpl.class.getInterfaces(), myInvocationHandler);
        helloProxy.sayHello();

        // Cglib动态代理
    }

    interface Hello {
        void sayHello();
    }

    static class HelloImpl implements Hello {

        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    static class MyInvocationHandler implements InvocationHandler {
        private Object target;

        public MyInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("前置增强：hello rich!!!");
            Object invoke = method.invoke(target, args);
            System.out.println("后置增强: hello finish");
            return invoke;
        }
    }
}
