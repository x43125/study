package com.wx.base.juc.advance.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Descrption: AtomicIntegerFieldUpdater 即 将对象的某个 属性包装起来 实现 原子操作：
 * 该属性必须：public修饰；非包装属性（Integer）；被 volatile 修饰
 * 如：public volatile int age
 * @Author: x43125
 * @Date: 22/05/04
 */
public class AtomicUpdaterStudy {
    static AtomicIntegerFieldUpdater<AtomicReferenceStudy.User> old = AtomicIntegerFieldUpdater.newUpdater(AtomicReferenceStudy.User.class, "age");

    public static void main(String[] args) {
        AtomicReferenceStudy.User user = new AtomicReferenceStudy.User("wanger", 33);
        System.out.println(old.getAndIncrement(user));
        System.out.println(old.get(user));
    }
}
