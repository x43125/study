package com.wx.base.juc.advance.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;

/**
 * @author Shawn
 * @date 2023/7/12 16:18
 * @description
 */
public class JUCUtils {
    public static void sysout(String content) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now + " " + Thread.currentThread().getName() + " " + content);
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

    public static void sleeper(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
