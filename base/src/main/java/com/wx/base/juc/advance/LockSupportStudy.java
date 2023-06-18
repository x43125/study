package com.wx.base.juc.advance;

import java.util.concurrent.locks.LockSupport;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/21
 */
public class LockSupportStudy {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start park");
                while (!Thread.currentThread().isInterrupted()) {
                    LockSupport.park();
                }
                System.out.println("end park");
            }
        });

        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread begin unpark");
        thread.interrupt();

    }
}
