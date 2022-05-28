package com.wx.base.design.pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/28
 */
public class JdkProxy {
    interface SmsService {
        String send(String msg);
    }

    static class SmsServiceImpl implements SmsService {

        public String send(String msg) {
            System.out.println("msg = " + msg);
            return msg;
        }
    }

    static class DynamicSmsServiceProxy implements InvocationHandler {
        private Object object;

        public DynamicSmsServiceProxy(Object object) {
            this.object = object;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //调用方法之前，我们可以添加自己的操作
            System.out.println("before method " + method.getName());
            Object result = method.invoke(object, args);
            //调用方法之后，我们同样可以添加自己的操作
            System.out.println("after method " + method.getName());
            return result;
        }
    }

    static class JdkProxyFactory {
        public static Object getProxy(Object object) {
            return Proxy.newProxyInstance(
                    object.getClass().getClassLoader(),
                    object.getClass().getInterfaces(),
                    new DynamicSmsServiceProxy(object)
            );
        }
    }

    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        String hello_dynamic_proxy = smsService.send("hello dynamic proxy");
        System.out.println("hello_dynamic_proxy = " + hello_dynamic_proxy);
    }
}
