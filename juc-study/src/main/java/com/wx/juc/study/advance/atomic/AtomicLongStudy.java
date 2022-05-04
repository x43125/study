package com.wx.juc.study.advance.atomic;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/21
 */
public class AtomicLongStudy {
    static java.util.concurrent.atomic.AtomicLong l = new java.util.concurrent.atomic.AtomicLong(1000L);

    public static void main(String[] args) {
        System.out.println(l);
        System.out.println("=====================================");
        new Thread(new AtomicSimulate02()).start();
        new Thread(new AtomicSimulate01()).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("=====================================");
        System.out.println(l);

    }

    static class AtomicSimulate01 implements Runnable {
        @Override
        public void run() {
            System.out.println("----------------------------------");
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread() + " : " + l.incrementAndGet());
            }
            System.out.println("----------------------------------");
        }
    }

    static class AtomicSimulate02 implements Runnable {
        @Override
        public void run() {
            System.out.println("++++++++++++++++++++++++++++++++++");
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread() + " : " + l.incrementAndGet());
            }
            System.out.println("++++++++++++++++++++++++++++++++++");
        }
    }

}
