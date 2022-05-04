package com.wx.juc.study.advance.utils;

import java.util.concurrent.Semaphore;

/**
 * @Descrption: semaphore 控制线程的个数
 * @Author: x43125
 * @Date: 22/05/04
 */
public class SemaphoreStudy {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 抢到了一个车位");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " 离开了车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            }, (i + 1) + "车🚗").start();
        }
    }
}
