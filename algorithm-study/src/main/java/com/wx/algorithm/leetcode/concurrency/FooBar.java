package com.wx.algorithm.leetcode.concurrency;

class FooBar {
    private int n;
    private Object o = new Object();

    private boolean finish = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (o) {
                while (!finish) {
                    o.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                finish = false;
                o.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (o) {
                while (finish) {
                    o.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                finish = true;
                o.notifyAll();
            }
        }
    }
}