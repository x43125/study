package com.wx.base.juc.advance;

import java.util.concurrent.Semaphore;

/**
 * @author Shawn
 * @date 2023/7/12 14:28
 * @description
 */
public class SemaphoreStudy01 {
    public static void main(String[] args) {
        // 1. 创建 semaphore
        Semaphore semaphore = new Semaphore(3);
        // 2. 10个线程运行
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    System.out.println("...running");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    System.out.println("release...");
                    semaphore.release();
                }
            }).start();
        }
    }
}
