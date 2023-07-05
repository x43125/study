package com.wx.base.juc.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/20
 */
public class InterruptStudy {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int time = 0;

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " hello");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                time++;
            }

            System.out.println(time + "ms");
        }, "t1");

        System.out.println("thread.isAlive() = " + thread.isAlive());
        thread.start();
        Thread.sleep(1);
        System.out.println(thread.getName() + ".isInterrupted() = " + thread.isInterrupted());
        System.out.println("thread.isAlive() = " + thread.isAlive());
        System.out.println("main thread interrupt thread");
        thread.interrupt();
        System.out.println(thread.getName() + ".isInterrupted() = " + thread.isInterrupted());
        thread.join();
        System.out.println("thread.isAlive() = " + thread.isAlive());
        System.out.println("main is over");
    }
}
