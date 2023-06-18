package com.wx.base.juc.advance.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Descrption: 让一组（声名的时候填写的，如下 barrierNo）线程都运行到 barrier.await处后，再继续运行
 * @Author: x43125
 * @Date: 22/05/04
 */
public class CyclicBarrierStudy {
    public static void main(String[] args) {
        testBarrier01();
//        System.out.println("====================================");
//        testBarrier02();
    }

    private static void testBarrier01() {
        int barrierNo = 4;
        CyclicBarrier barrier = new CyclicBarrier(barrierNo);
        for (int i = 0; i < barrierNo; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 开始上车");
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 发车");
            }).start();
        }
    }

    private static void testBarrier02() {
        CyclicBarrier barrier = new CyclicBarrier(4, ()-> {
            System.out.println("车满了，开始出发");
        });
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 开始上车");
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 发车");
            }).start();
        }
    }
}
