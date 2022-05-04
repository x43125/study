package com.wx.juc.study.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/14
 */
public class RunnableStudy implements Runnable {

    @Override
    public void run() {
        System.out.println("I am a child thread");
    }

    public static void main(String[] args) {
        RunnableStudy runnableStudy = new RunnableStudy();
        new Thread(runnableStudy).start();
        new Thread(runnableStudy).start();
        System.out.println("i am a main thread");
    }
}
