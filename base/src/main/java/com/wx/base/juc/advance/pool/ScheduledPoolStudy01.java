package com.wx.base.juc.advance.pool;

import com.wx.base.juc.advance.utils.JUCUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Shawn
 * @date 2023/7/13 22:29
 * @description
 */
public class ScheduledPoolStudy01 {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
//        pool.schedule(() -> {
//            JUCUtils.sysout("task1");
//            int i = 1 / 0;
//        }, 1, TimeUnit.SECONDS);
//
//        pool.schedule(() -> {
//            JUCUtils.sysout("task2");
//        }, 1, TimeUnit.SECONDS);
        JUCUtils.sysout("开始");
        pool.scheduleAtFixedRate(() -> {
            JUCUtils.sysout("running...");
            JUCUtils.sleeper(1000);
        }, 1, 1, TimeUnit.SECONDS);
    }
}
