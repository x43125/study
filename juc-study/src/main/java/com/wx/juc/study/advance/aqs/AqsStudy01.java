package com.wx.juc.study.advance.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Descrption: study AbstractQueuedSynchronizer
 * @Author: x43125
 * @Date: 22/05/04
 */
public class AqsStudy01 {
    public static void main(String[] args) {
//        testNoFairLock();
//        testFairLock();
        bankSimulate();
    }

    private static void testNoFairLock() {
        ReentrantLock noFairLock = new ReentrantLock();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                noFairLock.lock();
                System.out.println(Thread.currentThread().getName() + " 获取到锁了" + noFairLock.getQueueLength());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    noFairLock.unlock();
                }
            }, String.valueOf(i)).start();
        }
    }

    private static void testFairLock() {
        ReentrantLock fairLock = new ReentrantLock(true);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + " 获取到锁了" + fairLock.getQueueLength());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    fairLock.unlock();
                }
            }, String.valueOf(i)).start();
        }
    }

    private static void bankSimulate() {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " thread come in");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "A").start();
        new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " thread come in");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "B").start();
        new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " thread come in");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "C").start();

    }
}
