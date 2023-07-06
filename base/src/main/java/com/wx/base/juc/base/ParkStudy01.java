package com.wx.base.juc.base;

import java.util.concurrent.locks.LockSupport;

/**
 * @author wangxiang
 * @date 2023/7/6 16:02
 * @description
 */
public class ParkStudy01 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LockSupport.park();
            LockSupport.park();

        }, "t1");

        System.out.println("开启线程");
        t1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("unpark 线程");
        LockSupport.unpark(t1);


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("再次unpark");
        LockSupport.unpark(t1);

    }
}
