package com.wx.juc.study.advance;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/28
 */
public class ConcurrentStudy {
    static volatile int i = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    i++;
                    System.out.println(Thread.currentThread() + ":" + i);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    i++;
                    System.out.println(Thread.currentThread() + ":" + i);
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    i++;
                    System.out.println(Thread.currentThread() + ":" + i);
                }
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    i++;
                    System.out.println(Thread.currentThread() + ":" + i);
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        Thread.sleep(10);
        System.out.println("main thread!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! : " + i);
        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupt();
        thread4.interrupt();
    }
}
