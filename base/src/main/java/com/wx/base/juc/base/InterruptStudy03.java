package com.wx.base.juc.base;

import com.wx.base.juc.advance.utils.JUCUtils;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/20
 */
public class InterruptStudy03 {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (; ; ) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted) {
                    JUCUtils.sysout("被打断了...");
                    break;
                }
            }
        });

        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread.interrupt();
//        System.out.println("1:" + thread.isInterrupted());
//        System.out.println("2:" + thread.interrupted());
//        System.out.println("3:" + Thread.interrupted());
        System.out.println("4:" + thread.isInterrupted());
    }
}
