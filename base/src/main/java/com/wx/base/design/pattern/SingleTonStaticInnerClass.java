package com.wx.base.design.pattern;

/**
 * 静态内部类方式，懒汉式
 * @author wangxiang
 * @date 2023/7/8 11:39
 * @description
 */
public class SingleTonStaticInnerClass {
    private SingleTonStaticInnerClass() {
    }

    private static class LazyHolder {
        static final SingleTonStaticInnerClass INSTANCE = new SingleTonStaticInnerClass();
    }

    public static SingleTonStaticInnerClass getInstance() {
        return LazyHolder.INSTANCE;
    }
}
