package com.wx.base.juc.advance.pool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/04
 */
public class ThreadPoolStudy {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3,
                6,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(5),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            executor.execute(() -> {
                System.out.println(finalI + " " + Thread.currentThread().getName() + " 开始运行");
            });
        }

        executor.shutdown();
    }
}
