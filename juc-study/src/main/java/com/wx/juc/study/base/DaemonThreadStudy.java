package com.wx.juc.study.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/20
 */
public class DaemonThreadStudy {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is a daemon thread");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("daemon is over");
            }
        });

        thread.setDaemon(true);
        thread.start();

        Thread.sleep(1000);

        System.out.println("main thread is over");
    }
}
