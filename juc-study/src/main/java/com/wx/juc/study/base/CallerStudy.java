package com.wx.juc.study.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/14
 */
public class CallerStudy implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return Thread.currentThread().getName() + "hello";
    }

    public static void main(String[] args) {
        // 创建异步任务
        FutureTask<String> futureTask1 = new FutureTask<>(new CallerStudy());
        FutureTask<String> futureTask2 = new FutureTask<>(new CallerStudy());
        System.out.println("========");

        //启动线程
        Thread thread1 = new Thread(futureTask1);
        thread1.start();
        try {
            //等待任务执行完毕，并返回结果
            String result = futureTask1.get();
            System.out.println(result);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        thread1 = new Thread(futureTask2);
        thread1.start();
        System.out.println("--------");
        try {
            //等待任务执行完毕，并返回结果
            String result = futureTask2.get();
            System.out.println(result);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("++++++++");
    }
}
