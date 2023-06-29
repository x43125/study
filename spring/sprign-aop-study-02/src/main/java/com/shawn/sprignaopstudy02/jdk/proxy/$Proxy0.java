package com.shawn.sprignaopstudy02.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author wangxiang
 * @date 2023/6/29 11:18
 * @description
 */
public class $Proxy0 extends Proxy implements Foo {

    /**
     * Constructs a new {@code Proxy} instance from a subclass
     * (typically, a dynamic proxy class) with the specified value
     * for its invocation handler.
     *
     * @param h the invocation handler for this proxy instance
     * @throws NullPointerException if the given invocation handler, {@code h},
     *                              is {@code null}.
     */
    protected $Proxy0(InvocationHandler h) {
        super(h);
    }

    @Override
    public void foo() {
        try {
            h.invoke(this, foo, null);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    static Method foo;
    static {
        try {
            foo = Foo.class.getDeclaredMethod("foo");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
