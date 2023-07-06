package com.wx.base.juc.base;

/**
 * @author wangxiang
 * @date 2023/7/6 16:42
 * @description
 */
public class DeadLockStudy01 {
    static final Object o1 = new Object();
    static final Object o2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (o1) {
                System.out.println("get o1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("want to get o2");
                synchronized (o2) {
                    System.out.println("get o2");
                }
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            synchronized (o2) {
                System.out.println("get o2");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("want to get o1");
                synchronized (o1) {
                    System.out.println("get o1");
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
