package com.wx.base.juc.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/18
 */
public class WaitNotifyInterrupt {
    static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (object) {
                        System.out.println("----begin----");
                        object.wait(3000);
                        System.out.println("======end=====");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        Thread.sleep(3000);
        System.out.println("---begin interrupt threadA---");
        threadA.interrupt();
        System.out.println("--- end interrupt threadA ---");
    }
}
