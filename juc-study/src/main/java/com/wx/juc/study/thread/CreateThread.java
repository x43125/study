package com.wx.juc.study.thread;

import java.util.concurrent.*;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/18
 */
public class CreateThread {

    static class ExtendThread extends Thread {
        @Override
        public void run() {
            System.out.println("直接继承Thread 创建线程 " + currentThread().getName());
        }
    }

    static class ImplementRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("实现Runnable接口创建线程，需要依托Thread 或者线程池等创建线程 本身并非线程 " + Thread.currentThread().getName());
        }
    }

    static class ImplementCallable implements Callable<String> {

        @Override
        public String call() throws InterruptedException {
            System.out.println("实现Callable接口创建线程，可以通过FutureTask 接收返回值，" +
                    "调用接收方法即(FutureTask.get())方法的线程会被阻塞，直到获取到值 " +
                    "本身还需要依托Thread 并非线程 " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
            return Thread.currentThread().getName();
        }
    }


    public static void main(String[] args) {
        // 继承Thread
        ExtendThread extendThread = new ExtendThread();
        extendThread.start();

        // 实现runnable
        ImplementRunnable runnable = new ImplementRunnable();
        new Thread(runnable).start();

        //实现callable
        ImplementCallable callable = new ImplementCallable();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        try {
            System.out.println("Callable的返回值 调用此方法的线程会被阻塞，直到得到线程的返回值" + futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // 线程池启动线程 4种官方自带的
        // CachedThreadPool: maximumPoolSize = Integer.MAX_VALUE
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(runnable);
        // FixedThreadPool: corePoolSize = maximumPoolSize = nThreads && workQueue = new LinkedBlockingQueue()
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(runnable);
        // todo DelayedWorkQueue
        // ScheduledThreadPool: corePoolSize = nThreads && maximumPoolSize = Integer.MAX_VALUE && workQueue = new DelayedWorkQueue()
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.execute(runnable);
        // SingleThreadExecutor: corePoolSize = maximumPoolSize = 1 && workQueue = new LinkedBlockingQueue()
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(runnable);

        cachedThreadPool.shutdown();
        scheduledExecutorService.shutdown();
        executorService.shutdown();
        singleThreadExecutor.shutdown();
    }
}
