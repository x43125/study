package com.wx.base.juc.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/20
 */
public class ThreadLocalStudy02 {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("hello world");
        inheritableThreadLocal.set("hello java");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread: " + threadLocal.get());
                System.out.println("thread: " + inheritableThreadLocal.get());
            }
        });

        thread.start();

        System.out.println("main: " + threadLocal.get());
        System.out.println("main: " + inheritableThreadLocal.get());
    }
}
