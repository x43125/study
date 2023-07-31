package com.wx.base.juc.base;

import com.wx.base.StaticStudy;
import com.wx.base.juc.advance.utils.JUCUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Shawn
 * @date 2023/7/13 19:03
 * @description
 */
public class ReentrantLockStudy01 {
    private static ReentrantLock lock = new ReentrantLock();

    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            JUCUtils.sysout("尝试获得锁");
            try {
                if (!lock.tryLock(2, TimeUnit.SECONDS)) {
                    JUCUtils.sysout("获取不到锁");
                }
                condition1.await(1, TimeUnit.SECONDS);
                JUCUtils.sysout("获得锁");
            } catch (Exception e) {
                e.printStackTrace();
                JUCUtils.sysout("获取不到锁");
            }
        }, "t1");

        lock.lock();
        condition1.signal();
        JUCUtils.sysout("获得锁");
        t1.start();
        JUCUtils.sleeper(1000);
        JUCUtils.sysout("释放了锁");
        lock.unlock();
    }


}


class LockInterruptiblyExample {
    private Lock lock = new ReentrantLock();

    public void doWork() {
        try {
            // 尝试获取锁，如果线程被中断，则抛出InterruptedException
            lock.lock();
            try {
                // 执行需要同步的操作
                JUCUtils.sysout(Thread.currentThread().getName() + " has acquired the lock and is doing some work.");
                Thread.sleep(10000);
            } finally {
                // 释放锁
                lock.unlock();
                JUCUtils.sysout(Thread.currentThread().getName() + " has released the lock.");
            }
        } catch (InterruptedException e) {
            // 线程在等待锁的过程中被中断，可以在这里进行一些处理
            JUCUtils.sysout(Thread.currentThread().getName() + " has been interrupted while waiting for the lock.");
        }
    }

    public static void main(String[] args) {
        LockInterruptiblyExample example = new LockInterruptiblyExample();
        Runnable task = () -> example.doWork();

        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");

        thread1.start();
        thread2.start();

        // 等待一段时间后中断线程2
        try {
            Thread.sleep(1000);
            thread2.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}