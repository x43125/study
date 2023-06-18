package com.wx.base.juc.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/20
 */
public class InterruptStudy {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int time = 0;

                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread() + "hello");
                    time++;
                }

                System.out.println(time);
            }
        });

        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread interrupt thread");
        thread.interrupt();

        thread.join();
        System.out.println("main is over");
    }
}
