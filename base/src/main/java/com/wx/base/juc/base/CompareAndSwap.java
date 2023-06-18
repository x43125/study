package com.wx.base.juc.base;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Descrption: CAS
 * @Author: x43125
 * @Date: 21/11/20
 */
public class CompareAndSwap {

    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong(1000L);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread 1 ++ atomicLong");
                long l = atomicLong.incrementAndGet();
                System.out.println(l);
                System.out.println(atomicLong.getAndIncrement());
            }
        });

        thread.start();
    }
}
