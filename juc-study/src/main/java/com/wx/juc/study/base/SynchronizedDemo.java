package com.wx.juc.study.base;

/**
 * @Author: x43125
 * @Date: 22/03/01
 */
public class SynchronizedDemo {
    public void method() {
        synchronized (this) {
            System.out.println("synchronized code block");
        }
    }
}
