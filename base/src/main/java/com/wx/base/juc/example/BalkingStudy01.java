package com.wx.base.juc.example;

/**
 * 希望doInit()只被调用一次，下面的代码可行吗？
 * 答：不行，
 * 修改：直接加synchronized
 *
 * @author wangxiang
 * @date 2023/7/8 11:10
 * @description
 */
public class BalkingStudy01 {
    volatile boolean initialized = false;

    synchronized void init() {
        if (initialized) {
            return;
        }

        doInit();
        initialized = true;
    }

    private void doInit() {
        System.out.println("只会运行一次");
    }

    public static void main(String[] args) {
        BalkingStudy01 balkingStudy01 = new BalkingStudy01();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> balkingStudy01.init()).start();
        }
    }
}
