package com.wx.base.juc.advance.pool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangxiang
 * @date 2023/7/9 13:35
 * @description
 */
public class ThreadPoolCustom {

}

class BlockingQueue<T> {
    /**
     * 任务队列
     */
    private Deque<T> queue = new ArrayDeque<>();
    /**
     * 锁
     */
    private ReentrantLock lock = new ReentrantLock();
    /**
     * 生产者条件变量
     */
    private Condition fullWaitSet = lock.newCondition();
    /**
     * 消费者条件变量
     */
    private Condition emptyWaitSet = lock.newCondition();
    /**
     * 容量:最大容量
     */
    private int capacity;

    public T poll(long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            // 当队列为空的时候，等待;当不为空的时候，则返回头节点任务
            while (queue.isEmpty()) {
                // 等待队列插值的时候唤醒
                // 返回值是 等待时间-已经经过的时间 = 剩余应该等待的时间
                if (nanos <= 0) {
                    return null;
                }
                nanos = emptyWaitSet.awaitNanos(nanos);
            }
            T headTask = queue.removeFirst();
            // 当取出了一个任务后，队列则非满状态，此时可以唤醒 fullWaitSet 等待
            fullWaitSet.signal();
            return headTask;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 从任务队列中取任务
     *
     * @return
     */
    public T take() {
        lock.lock();
        try {
            // 当队列为空的时候，等待;当不为空的时候，则返回头节点任务
            while (queue.isEmpty()) {
                // 等待队列插值的时候唤醒
                emptyWaitSet.wait();
            }
            T headTask = queue.removeFirst();
            // 当取出了一个任务后，队列则非满状态，此时可以唤醒 fullWaitSet 等待
            fullWaitSet.signal();
            return headTask;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void put(T task) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                fullWaitSet.wait();
            }
            // 当添加一个任务进队列后，队列则非空状态，此时可以唤醒 emptyWaitSet 等待
            queue.addLast(task);
            emptyWaitSet.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}
