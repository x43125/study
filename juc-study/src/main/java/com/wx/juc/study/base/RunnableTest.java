package com.wx.juc.study.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/14
 */
public class RunnableTest implements Runnable {

    @Override
    public void run() {
        System.out.println("I am a child thread");
    }

    public static void main(String[] args) {
        RunnableTest runnableTest = new RunnableTest();
        new Thread(runnableTest).start();
        new Thread(runnableTest).start();
        System.out.println("i am a main thread");
    }
}
