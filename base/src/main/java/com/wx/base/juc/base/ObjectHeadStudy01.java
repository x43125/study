package com.wx.base.juc.base;

import com.wx.base.juc.advance.utils.JUCUtils;
import com.wx.base.juc.base.model.Dog;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author wangxiang
 * @date 2023/7/6 10:42
 * @description
 */
public class ObjectHeadStudy01 {
    public static void main(String[] args) throws InterruptedException {
        Dog dog1 = new Dog();
        JUCUtils.sysout("初始对象头：\n" + ClassLayout.parseInstance(dog1).toPrintable());
        Thread.sleep(5000);
        Dog dog = new Dog();
        JUCUtils.sysout("休息5秒后，又建了一个对象的对象头：" + ClassLayout.parseInstance(dog).toPrintable());
        synchronized (dog) {
            JUCUtils.sysout("主线程加了锁之后： " + ClassLayout.parseInstance(dog).toPrintable());
        }

        synchronized (dog) {
            JUCUtils.sysout("主线程加了锁之后： " + ClassLayout.parseInstance(dog).toPrintable());
        }

        new Thread(() -> {
            synchronized (dog) {
                JUCUtils.sysout("t1 线程加锁之后: " + ClassLayout.parseInstance(dog).toPrintable());
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (dog) {
                JUCUtils.sysout("t2 线程加锁之后: " + ClassLayout.parseInstance(dog).toPrintable());
            }
        }, "t2").start();

        Thread.sleep(5000);
        JUCUtils.sysout("又睡了5秒后，主线程: " + ClassLayout.parseInstance(dog).toPrintable());
    }
}
