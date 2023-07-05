package com.wx.base.juc.base;

/**
 * @author wangxiang
 * @date 2023/7/5 16:42
 * @description
 */
public class UnsafePlusSub {
    static volatile Integer i = 0;
    static Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
//            synchronized (o) {
                for (int j = 0; j < 100000; j++) {
                    i++;
                }
//            }
        });
        Thread thread1 = new Thread(() -> {
//            synchronized (o) {
                for (int j = 0; j < 100000; j++) {
                    i--;
                }
//            }
        });
        thread1.start();
        thread.start();
        thread1.join();
        thread.join();
        System.out.println(i);
    }
}
