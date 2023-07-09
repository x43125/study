package com.wx.base.juc.advance.pool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangxiang
 * @date 2023/7/9 13:35
 * @description
 */
public class ThreadPoolCustom {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(1, 1000, TimeUnit.MILLISECONDS, 1, ((queue, task) -> {
            // 1.死等
//            queue.put(task);
            // 2.带超时等待
            queue.offer(task, 500, TimeUnit.MILLISECONDS);
        }));

        for (int i = 0; i < 3; i++) {
            int j = i;
            pool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(j);
            });
        }

    }
}

@FunctionalInterface
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}

class ThreadPool {
    /**
     * 任务队列：任务具体要执行的内容集合
     */
    private BlockingQueue<Runnable> taskQueue;
    /**
     * 线程集合：Thread集合，用于执行上面的任务
     */
    private HashSet<Worker> workers = new HashSet<>();
    private int coreSize;
    private long timeout;
    private TimeUnit timeUnit;

    private RejectPolicy<Runnable> rejectPolicy;

    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                System.out.println("新增 worker: " + worker + ", " + task);
                workers.add(worker);
                worker.start();
            } else {
//                taskQueue.put(task);
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
        this.rejectPolicy = rejectPolicy;
    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            // 执行任务
            // 1）当task不为空，执行任务
            // 2）当task执行完毕，再接着从任务队列获取任务并执行
            while (task != null || (task = taskQueue.take()) != null) {
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }

            synchronized (workers) {
                workers.remove(this);
            }
        }
    }
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

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

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
            // 当队列为空的时候，等待; 当不为空的时候，则返回头节点任务
            while (queue.isEmpty()) {
                // 等待队列插值的时候唤醒
                emptyWaitSet.await();
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

    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capacity) {
                System.out.println("等待加入任务队列: " + task);
                if (nanos <= 0) {
                    return false;
                }
                nanos = fullWaitSet.awaitNanos(nanos);
            }
            // 当添加一个任务进队列后，队列则非空状态，此时可以唤醒 emptyWaitSet 等待
            System.out.println("加入任务队列: " + task);
            queue.addLast(task);
            emptyWaitSet.signal();
            return true;
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

    public void put(T task) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                System.out.println("等待加入任务队列: " + task);
                fullWaitSet.await();
            }
            // 当添加一个任务进队列后，队列则非空状态，此时可以唤醒 emptyWaitSet 等待
            System.out.println("加入任务队列: " + task);
            queue.addLast(task);
            emptyWaitSet.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            if (queue.size() == capacity) {
                rejectPolicy.reject(this, task);
            } else {
                System.out.println("加入任务队列: " + task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
