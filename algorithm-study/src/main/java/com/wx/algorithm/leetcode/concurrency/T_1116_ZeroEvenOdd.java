package com.wx.algorithm.leetcode.concurrency;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/15
 */
public class T_1116_ZeroEvenOdd {
    private final int n;
    private volatile int state;
    private volatile boolean change = true;

    public T_1116_ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(Integer printNumber) {
        for (int i = 0; i < n; i++) {
            while (state != 0) {
                Thread.yield();
            }
            System.out.print(0 + " ");
            state = change ? 1 : 2;
        }
    }

    public void even(Integer printNumber) {
        for (int i = 2; i <= n; i += 2) {
            while (state != 2) {
                Thread.yield();
            }
            System.out.print(i + " ");
            change = true;
            state = 0;
        }
    }

    public void odd(Integer printNumber) {
        for (int i = 1; i <= n; i += 2) {
            while (state != 1) {
                Thread.yield();
            }
            System.out.print(i + " ");
            change = false;
            state = 0;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        T_1116_ZeroEvenOdd zeroEvenOdd = new T_1116_ZeroEvenOdd(n);
        new Thread(() -> zeroEvenOdd.zero(n)).start();
        new Thread(() -> zeroEvenOdd.even(n)).start();
        new Thread(() -> zeroEvenOdd.odd(n)).start();
    }
}
