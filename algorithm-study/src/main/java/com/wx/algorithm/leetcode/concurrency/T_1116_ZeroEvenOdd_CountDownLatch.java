package com.wx.algorithm.leetcode.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/15
 */
public class T_1116_ZeroEvenOdd_CountDownLatch {
    private final int n;
    private CountDownLatch zeroLatch = new CountDownLatch(0);
    private CountDownLatch evenLatch = new CountDownLatch(1);
    private CountDownLatch oddLatch = new CountDownLatch(1);

    public T_1116_ZeroEvenOdd_CountDownLatch(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(Integer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            zeroLatch.await();
            System.out.print(0 + " ");
            zeroLatch = new CountDownLatch(1);
            if ((i & 1) == 1) {
                evenLatch.countDown();
            } else {
                oddLatch.countDown();
            }
        }
    }

    public void even(Integer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            evenLatch.await();
            System.out.print(i + " ");
            zeroLatch.countDown();
            evenLatch = new CountDownLatch(1);
        }
    }

    public void odd(Integer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            oddLatch.await();
            System.out.print(i + " ");
            zeroLatch.countDown();
            oddLatch = new CountDownLatch(1);
        }
    }

    public static void main(String[] args) {
        int n = 5;
        T_1116_ZeroEvenOdd_CountDownLatch zeroEvenOdd = new T_1116_ZeroEvenOdd_CountDownLatch(n);
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(n);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(n);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd(n);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
