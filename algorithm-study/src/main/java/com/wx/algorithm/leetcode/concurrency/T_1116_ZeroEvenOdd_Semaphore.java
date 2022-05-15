package com.wx.algorithm.leetcode.concurrency;

import java.util.concurrent.Semaphore;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/15
 */
public class T_1116_ZeroEvenOdd_Semaphore {
    private final int n;
    private final Semaphore zeroSemaphore = new Semaphore(1);
    private final Semaphore evenSemaphore = new Semaphore(0);
    private final Semaphore oddSemaphore = new Semaphore(0);

    public T_1116_ZeroEvenOdd_Semaphore(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(Integer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            zeroSemaphore.acquire();
            System.out.print(0 + " ");
            if ((i & 1) == 1) {
                evenSemaphore.release();
            } else {
                oddSemaphore.release();
            }
        }
    }

    public void even(Integer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i+=2) {
            evenSemaphore.acquire();
            System.out.print(i + " ");
            zeroSemaphore.release();
        }
    }

    public void odd(Integer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i+=2) {
            oddSemaphore.acquire();
            System.out.print(i + " ");
            zeroSemaphore.release();
        }
    }

    public static void main(String[] args) {
        int n = 5;
        T_1116_ZeroEvenOdd_Semaphore zeroEvenOdd = new T_1116_ZeroEvenOdd_Semaphore(n);
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
