package com.wx.base.juc.advance;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * fork join 针对可以拆分的任务，CPU密集型的任务
 *
 * @author Shawn
 * @date 2023/7/11 23:50
 * @description
 */
public class ForkJoinStudy01 {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);
        System.out.println(pool.invoke(new MyTask(5)));
    }


}

class MyTask extends RecursiveTask<Integer> {
    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n == 1) {
            return 1;
        }

        MyTask t1 = new MyTask(n - 1);
        t1.fork();
        int result = n + t1.join();

        return result;
    }
}