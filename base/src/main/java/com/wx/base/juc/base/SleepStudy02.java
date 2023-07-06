package com.wx.base.juc.base;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/20
 */
public class SleepStudy02 {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("child threadA is in sleep");
                Thread.sleep(3000);
                System.out.println("child threadA is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread threadB = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("child threadB is in sleep");
                Thread.sleep(3000);
                System.out.println("child threadB is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread threadC = new Thread(() -> {
            System.out.println("child threadC is in sleep");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("child threadC is in awaked");
        });

        threadA.start();
        threadB.start();
        threadC.start();
//        Thread.sleep(2000);
//        threadA.interrupt();
    }
}
