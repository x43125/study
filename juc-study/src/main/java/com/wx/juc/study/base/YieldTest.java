package com.wx.juc.study.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/20
 */
public class YieldTest implements Runnable {

    YieldTest() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; ++i) {
            if (i % 5 == 0) {
                System.out.println(Thread.currentThread() + "yield cpu...");
//                Thread.yield();
            }
        }

        System.out.println(Thread.currentThread() + " is over");
    }

    public static void main(String[] args) {
        new YieldTest();
        new YieldTest();
        new YieldTest();
    }
}
