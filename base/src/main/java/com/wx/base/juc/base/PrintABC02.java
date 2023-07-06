package com.wx.base.juc.base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangxiang
 * @date 2023/7/6 21:14
 * @description
 */
public class PrintABC02 {
    public static void main(String[] args) throws InterruptedException {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();

        new Thread(() -> awaitSignal.print("a", a, b)).start();
        new Thread(() -> awaitSignal.print("b", b, c)).start();
        new Thread(() -> awaitSignal.print("c", c, a)).start();

        Thread.sleep(1000);
        System.out.println("开始循环");
        awaitSignal.lock();
        try {
            a.signal();
        } finally {
            awaitSignal.unlock();
        }
    }

}

class AwaitSignal extends ReentrantLock {
    private final int loopNumber;

    public AwaitSignal(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    /**
     * 打印算法，当前线程打印什么内容，当前线程的condition，下一个待唤醒的线程
     *
     * @param str
     * @param current
     * @param next
     */
    public void print(String str, Condition current, Condition next) {
        for (int i = 0; i < loopNumber; i++) {
            this.lock();
            try {
                current.await();
                System.out.print(str);
                // 唤醒下一个线程
                next.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                this.unlock();
            }
        }
    }
}