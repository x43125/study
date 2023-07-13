package com.wx.base.juc.base;

import com.wx.base.juc.advance.utils.JUCUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Shawn
 * @date 2023/7/13 19:03
 * @description
 */
public class ReentrantLockStudy01 {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            JUCUtils.sysout("尝试获得锁");
            try {
                if (!lock.tryLock(2, TimeUnit.SECONDS)) {
                    JUCUtils.sysout("获取不到锁");
                }
                JUCUtils.sysout("获得锁");
            } catch (Exception e) {
                e.printStackTrace();
                JUCUtils.sysout("获取不到锁");
            }
        }, "t1");

        lock.lock();
        JUCUtils.sysout("获得锁");
        t1.start();
        JUCUtils.sleeper(1000);
        JUCUtils.sysout("释放了锁");
        lock.unlock();
    }
}
