package com.wx.base.juc.advance.utils;

/**
 * @author Shawn
 * @date 2023/7/12 16:18
 * @description
 */
public class JUCUtils {
    public static void sysout(String content) {
        System.out.println(Thread.currentThread().getName() + " " + content);
    }

    public static void sysout(Long content) {
        System.out.println(Thread.currentThread().getName() + " " + content);
    }

    public static void sysout(Integer content) {
        System.out.println(Thread.currentThread().getName() + " " + content);
    }

    public static void sysout(Character content) {
        System.out.println(Thread.currentThread().getName() + " " + content);
    }
}
