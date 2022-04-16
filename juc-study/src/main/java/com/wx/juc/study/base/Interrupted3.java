package com.wx.juc.study.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/20
 */
public class Interrupted3 {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                for (; ; ) {

                }
            }
        });

        thread.start();
        thread.interrupt();
        System.out.println("1:" + thread.isInterrupted());
        System.out.println("2:" + thread.interrupted());
        System.out.println("3:" + Thread.interrupted());
        System.out.println("4:" + thread.isInterrupted());
    }
}
