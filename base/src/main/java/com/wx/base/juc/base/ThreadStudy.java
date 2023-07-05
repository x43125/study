package com.wx.base.juc.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/14
 */
public class ThreadStudy {

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("I am a child thread");
            try {
                while (true) {
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThread 休息3s后");
        }
    }

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        myThread1.start();
//        myThread1.start();
        myThread2.start();
//        myThread2.start();

        System.out.println("I am a main thread");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main Thread 休息1s后");
    }

}
