package com.wx.base.juc.base;

import java.util.concurrent.locks.LockSupport;

/**
 * @author wangxiang
 * @date 2023/7/6 21:41
 * @description
 */
public class PrintAbc03 {

    static Thread t1;
    static Thread t2;
    static Thread t3;
    public static void main(String[] args) {
        ParkUnPark pu = new ParkUnPark(5);
        t1 = new Thread(() -> pu.print("a", t2));
        t2 = new Thread(() -> pu.print("b", t3));
        t3 = new Thread(() -> pu.print("c", t1));

        t1.start();
        t2.start();
        t3.start();

        LockSupport.unpark(t1);
    }
}

class ParkUnPark {
    private final int loopNumber;

    public ParkUnPark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Thread next) {
        for (int i = 0; i < loopNumber; i++) {
            LockSupport.park();
            System.out.print(str);
            LockSupport.unpark(next);
        }
    }
}
