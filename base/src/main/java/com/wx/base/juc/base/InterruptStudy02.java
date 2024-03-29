package com.wx.base.juc.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/20
 */
public class InterruptStudy02 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("child thread begin sleep for 2000 seconds");
                Thread.sleep(2000000);
                System.out.println("thread awaking");
            } catch (InterruptedException e) {
                System.out.println("thread is interrupted while sleeping");
                return;
            }
            System.out.println("thread leaving normally");
        });

        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        thread.join();
        System.out.println("main thread is over");
    }
}
