package com.wx.base.juc.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/20
 */
public class DeadLockStudy2 {

    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + "get ResourceA");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread() + "waiting get ResourceB");
                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread() + "get ResourceB");
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + "get resourceB");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread() + "waiting get resourceA");
                    synchronized (resourceA) {
                        System.out.println(Thread.currentThread() + "get resourceA");
                    }
                }
            }
        });

        threadA.start();
        threadB.start();


    }
}
