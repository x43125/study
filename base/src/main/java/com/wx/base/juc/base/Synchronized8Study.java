package com.wx.base.juc.base;


import com.wx.base.juc.advance.utils.JUCUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxiang
 * @date 2023/7/5 22:52
 * @description
 */
public class Synchronized8Study {
    static Object o = new Object();
    static Object o2 = new Object();

    public synchronized static void print() {
        JUCUtils.sysout("打印打印");
        JUCUtils.sleeper(1000);
    }

    public static void main(String[] args) {

        new Thread(() -> {
            print();
//            synchronized (o) {
//                JUCUtils.sysout("锁住了o，等待释放");
//                JUCUtils.sleeper(1000);
//            }
        }).start();

        new Thread(() -> {
            print();
//            synchronized (o2) {
//                JUCUtils.sysout("锁住了o2，等待释放");
//                JUCUtils.sleeper(1000);
//            }
        }).start();
    }
}
