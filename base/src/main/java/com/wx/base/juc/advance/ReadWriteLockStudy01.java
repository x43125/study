package com.wx.base.juc.advance;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Shawn
 * @date 2023/7/12 11:13
 * @description
 */
public class ReadWriteLockStudy01 {
    private Object object;
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock r = rw.readLock();
    private ReentrantReadWriteLock.WriteLock w = rw.writeLock();

    public Object read() {
        System.out.println("获取读锁");
        r.lock();
        try {
            System.out.println("读取");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return object;
        } finally {
            System.out.println("释放读锁");
            r.unlock();
        }
    }

    public void write(Object o) {
        System.out.println("获取写锁");
        w.lock();
        try {
            System.out.println("写入");
            this.object = o;
        } finally {
            System.out.println("释放写锁");
            w.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockStudy01 readWriteLockStudy01 = new ReadWriteLockStudy01();
        new Thread(() -> {
            Object read = readWriteLockStudy01.read();
            System.out.println("read = " + read);
        }).start();
        new Thread(() -> {
            Object read = readWriteLockStudy01.read();
            System.out.println("read = " + read);
        }).start();
        new Thread(() -> {
            readWriteLockStudy01.write(new Object());
        }).start();
        new Thread(() -> {
            Object read = readWriteLockStudy01.read();
            System.out.println("read = " + read);
        }).start();
        new Thread(() -> {
            readWriteLockStudy01.write(new Object());
        }).start();

    }
}
