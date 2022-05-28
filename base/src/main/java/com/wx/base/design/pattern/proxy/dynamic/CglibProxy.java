package com.wx.base.design.pattern.proxy.dynamic;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/29
 */
public class CglibProxy {
    public static class AliSmsService {
        public String send(String message) {
            System.out.println("send message: " + message);
            return message;
        }
    }

    static class CglibMethodInterceptor implements MethodInterceptor {

        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("before method send()");
            Object o1 = methodProxy.invokeSuper(o, objects);
            System.out.println("after method send()");

            return o1;
        }
    }

    static class CglibProxyFactory {

        public static Object getProxy(Class<?> clazz) {
            // 创建动态代理增强类
            Enhancer enhancer = new Enhancer();
            // 设置类加载器
            enhancer.setClassLoader(clazz.getClassLoader());
            // 设置被代理类
            enhancer.setSuperclass(clazz);
            // 设置方法拦截器
            enhancer.setCallback(new CglibMethodInterceptor());
            // 创建代理类
            return enhancer.create();
        }
    }

    public static void main(String[] args) {
        AliSmsService aliSmsService = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        String helloCglib = aliSmsService.send("hello cglib");
        System.out.println("helloCglib = " + helloCglib);
    }
}
