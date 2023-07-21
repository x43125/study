package com.shawn.springaopstudy01.proxy.jdk.principal;

import com.shawn.springaopstudy01.proxy.jdk.principal.JdkProxySimulate.*;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author wangxiang
 * @date 2023/6/29 10:02
 * @description
 */
public class $Proxy0 implements Foo {

    static Method foo;
    static Method bar;

    static {
        try {
            foo = Foo.class.getDeclaredMethod("foo");
            bar = Foo.class.getDeclaredMethod("bar");
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    private InvocationHandler ih;

    public $Proxy0(InvocationHandler ih) {
        this.ih = ih;
    }

    @Override
    public void foo() {
//        // 1. 功能增强 这部分的代码实际的时候不可能像这样写死在这里，所以需要抽出来
//        System.out.println("before...");
//        // 2. 调用目标
//        new Target().foo();

        // 于是在此处需要再加上一些参数
        try {
            // 提取出去，防止重复操作
//            Method foo = Foo.class.getDeclaredMethod("foo");
            ih.invoke(this, foo, new Object[0]);
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public int bar() {
        try {
//            Method bar = Foo.class.getDeclaredMethod("bar");
            Object o = ih.invoke(this, bar, new Object[0]);
            return (int) o;
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }
}
