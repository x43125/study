package com.wx.base.design.pattern;

import java.io.Serializable;

/**
 * @author wangxiang
 * @date 2023/7/8 10:32
 * @description
 */
// 问题1: 为什么加final？ 答：防止子类重写破坏单例
// 问题2: 如果实现了序列化接口，还要做什么来防止反序列化破坏单例？ 答：加一个 readResolve()，直接返回实例化对象
public final class SingleTonStatic implements Serializable {
    // 问题3:为什么设置为私有？ 答：为了防止直接创建实例
    // 问题3.1: 设置为私有，是否可以防止反射创建新实例？ 答：不行，可以 setAccessible(true)来暴力反射
    private SingleTonStatic() {
    }

    // 问题4: 这样初始化是否能保证单例对象创建时的线程安全？ 答：可以,你永远可以相信JVM
    private static final SingleTonStatic INSTANCE = new SingleTonStatic();

    // 问题5: 为什么提供静态方法而不是直接将 INSTANCE 设置为public？
    // 答：可以后期改成懒汉式；创建的时候可以有更多的控制；可以提供一些范型支持
    public static SingleTonStatic getInstance() {
        return INSTANCE;
    }

    public Object readResolve() {
        return INSTANCE;
    }
}
