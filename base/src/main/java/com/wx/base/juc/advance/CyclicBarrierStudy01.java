package com.wx.base.juc.advance;

import com.wx.base.juc.advance.utils.JUCUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Shawn
 * @date 2023/7/12 17:07
 * @description
 */
public class CyclicBarrierStudy01 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        CyclicBarrier barrier = new CyclicBarrier(2, () -> JUCUtils.sysout("task1, task2 finish..."));

//        for (int i = 0; i < 3; i++) {
        service.submit(() -> {
            JUCUtils.sysout("task1 begin...");
            JUCUtils.sleeper(1);
            try {
                barrier.await();
                JUCUtils.sysout("task1 end...");
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });

        service.submit(() -> {
            JUCUtils.sysout("task2 begin...");
            JUCUtils.sleeper(2);
            try {
                barrier.await();
                JUCUtils.sysout("task2 end...");
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });
//        }

        service.shutdown();
    }
}
