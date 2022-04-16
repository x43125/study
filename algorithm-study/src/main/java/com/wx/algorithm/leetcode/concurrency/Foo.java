package com.wx.algorithm.leetcode.concurrency;

public class Foo {
    Object o = new Object();
    boolean firstFinished = false;
    boolean secondFinished = false;

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (o) {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstFinished = true;
            o.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (o) {
            while (!firstFinished) {
                o.wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondFinished = true;
            o.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (o) {
            while (!secondFinished) {
                o.wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    public static void main(String[] args) {
        
    }

}
