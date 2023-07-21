package com.shawn.springaopstudy01.proxy.jdk.examples.example01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Shawn
 * @date 2023/7/21 17:31
 * @description
 */
public class ProxyExample01 {
    public static void main(String[] args) {
        ProxyExample proxyExa = new RealObject();

        MyInvocationhandler handler = new MyInvocationhandler(proxyExa);

        ProxyExample instance = (ProxyExample) Proxy.newProxyInstance(
                proxyExa.getClass().getClassLoader(),
                proxyExa.getClass().getInterfaces(),
                handler
        );

        instance.doSomething();
    }
}

interface ProxyExample {
    void doSomething();
}

class RealObject implements ProxyExample {

    @Override
    public void doSomething() {
        System.out.println("something");
    }
}

class MyInvocationhandler implements InvocationHandler {
    private Object object;

    public MyInvocationhandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method invocation...");
        Object invoke = method.invoke(object, args);
        System.out.println("after method invocation....");
        return invoke;
    }
}
