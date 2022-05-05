package com.wx.juc.study.advance.volatilestudy;

import java.util.concurrent.TimeUnit;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/05
 */
public class VolatileStudy {

    int no = 0;

    boolean flag = false;

    private void inc() {
        no++;
    }

    public static void main(String[] args) {
//        testAtomic();
        testReSort();
    }

    private static void testReSort() {
        VolatileStudy study = new VolatileStudy();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                study.no = 1;
                study.flag = true;
                if (study.flag) {
                    int b = study.no + 1;
                    System.out.println(b);
                }
            }).start();
        }
    }

    private static void testAtomic() {
        VolatileStudy study = new VolatileStudy();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    study.inc();
                    System.out.println(Thread.currentThread().getName() + " " + study.no);
                }
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("no: " + study.no);
    }
}