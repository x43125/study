package com.wx.base.juc.advance;

import com.wx.base.juc.advance.utils.JUCUtils;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Shawn
 * @date 2023/7/12 16:07
 * @description
 */
public class CountDownLatchStudy01 {
    public static void main(String[] args) {
//        CountDownLatch latch = new CountDownLatch(3);
////        new Thread(() -> {
////            System.out.println(Thread.currentThread().getName() + ": doing...");
////            latch.countDown();
////        }).start();
////
////        new Thread(() -> {
////            System.out.println(Thread.currentThread().getName() + ": doing...");
////            latch.countDown();
////        }).start();
////
////        new Thread(() -> {
////            System.out.println(Thread.currentThread().getName() + ": doing...");
////            latch.countDown();
////        }).start();
////
////        new Thread(() -> {
////            System.out.println(Thread.currentThread().getName() + ": doing...");
////            latch.countDown();
////        }).start();
//
//        ExecutorService service = Executors.newFixedThreadPool(4);
//
//        service.submit(() -> {
//            try {
//                JUCUtils.sysout(": doing...");
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            latch.countDown();
//            JUCUtils.sysout(latch.getCount());
//        });
//
//        service.submit(() -> {
//            try {
//                JUCUtils.sysout(": doing...");
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            latch.countDown();
//            JUCUtils.sysout(latch.getCount());
//        });
//        service.submit(() -> {
//            try {
//                JUCUtils.sysout(": doing...");
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            latch.countDown();
//            JUCUtils.sysout(latch.getCount());
//        });
//        service.submit(() -> {
//            try {
//                JUCUtils.sysout(": waiting...");
//                latch.await();
//                JUCUtils.sysout(": wait down...");
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            latch.countDown();
//            JUCUtils.sysout(latch.getCount());
//        });
//
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("3个线程都已经执行完成");

        CountDownLatchStudy01 countDownLatchStudy01 = new CountDownLatchStudy01();
        countDownLatchStudy01.gameLoading();
    }

    public void gameLoading() {
        CountDownLatch latch = new CountDownLatch(10);
        ExecutorService service = Executors.newFixedThreadPool(10);

        String[] allKey = new String[10];
        Random random = new Random();
        for (int j = 0; j < 10; j++) {
            int finalJ = j;
            service.submit(() -> {
                for (int i = 0; i <= 100; i++) {
                    JUCUtils.sleeper(random.nextInt(100));
                    allKey[finalJ] = i + "%";
                    System.out.print("\r" + Arrays.toString(allKey));
                }
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JUCUtils.sysout("\n所有玩家加载完毕：欢迎来到德莱联盟");
    }
}
