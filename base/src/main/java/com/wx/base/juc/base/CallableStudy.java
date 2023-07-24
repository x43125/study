package com.wx.base.juc.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/14
 */
public class CallableStudy implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return Thread.currentThread().getName() + " hello";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建异步任务
        FutureTask<String> futureTask1 = new FutureTask<>(new CallableStudy());
        FutureTask<String> futureTask2 = new FutureTask<>(new CallableStudy());
        System.out.println("========");

        //启动线程
        Thread thread1 = new Thread(futureTask1, "t1");
        thread1.start();
        Thread thread2 = new Thread(futureTask2, "t2");
        thread2.start();
        //等待任务执行完毕，并返回结果
        String result1 = futureTask1.get();
        System.out.println(result1);
        System.out.println("--------");

        //等待任务执行完毕，并返回结果
        String result2 = futureTask2.get();
        System.out.println(result2);
        System.out.println("++++++++");
    }
}
