package com.wx.juc.study.advance.utils;

import java.util.concurrent.CountDownLatch;

/**
 * @Descrption: CountDownLatch: 事先指定一个线程运行结束数，只有当有这个数字这么多的线程都运行完了，执行了 countDownLatch.await() 的线程才会继续执行
 * 如下，只有当所有同学都走光了，才能锁门
 * @Author: x43125
 * @Date: 22/05/04
 */
public class CountDownLatchStudy {
    public static void main(String[] args) {
        int latchNo = 5;
        CountDownLatch latch = new CountDownLatch(latchNo);
        for (int i = 0; i < latchNo; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 离开了教师");
                latch.countDown();
            }, "同学" + i).start();
        }

        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "： 所有人都走光了，我锁门了");
        }, "班长").start();

        System.out.println("main thread done");
    }
}
