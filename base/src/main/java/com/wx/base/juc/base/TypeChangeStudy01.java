package com.wx.base.juc.base;

/**
 * @author wangxiang
 * @date 2023/7/6 16:25
 * @description
 */
public class TypeChangeStudy01 {
    final static Object o = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (o) {
                try {
                    System.out.println("t1 waiting");
                    o.wait();
                    System.out.println("t1 awaked");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("其他代码");
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (o) {
                try {
                    System.out.println("t2 waiting");
                    o.wait();
                    System.out.println("t2 awaked");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("其他代码");
            }
        });

        t1.start();
        t2.start();

        System.out.println("2s 后唤醒所有线程");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (o) {
            o.notifyAll();
        }
    }
}
