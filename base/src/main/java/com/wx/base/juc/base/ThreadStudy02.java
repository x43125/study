package com.wx.base.juc.base;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangxiang
 * @date 2023/7/4 23:11
 * @description
 */
public class ThreadStudy02 {
    private int cnt = 0;

    public void add() {
        cnt++;
    }

    public int get() {
        return cnt;
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadStudy02 threadStudy02 = new ThreadStudy02();
//        threadStudy01.threadUnsafeExample();

        threadStudy02.vectorUnsafeExample();
    }

    /**
     * 线程不安全的例子
     * @throws InterruptedException
     */
    private void threadUnsafeExample() throws InterruptedException {
        final int threadSize = 1000;
        ThreadStudy02 example = new ThreadStudy02();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                example.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(example.get());
    }

    private static Vector<Integer> vector = new Vector<>();
    private void vectorUnsafeExample() {
        while (true) {
            for (int i = 0; i < 100; i++) {
                vector.add(i);
            }
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(() -> {
                synchronized (vector) {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            });
            executorService.execute(() -> {
                synchronized (vector) {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            });
            executorService.shutdown();
        }
    }
}
