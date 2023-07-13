package com.wx.base.juc.base;

import com.wx.base.juc.advance.utils.JUCUtils;

import java.util.concurrent.locks.LockSupport;

/**
 * @author wangxiang
 * @date 2023/7/6 16:02
 * @description
 */
public class ParkStudy01 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            JUCUtils.sleeper(1000);
            JUCUtils.sysout("park");
            LockSupport.park();
            JUCUtils.sysout("park completed");
        }, "t1");

        t1.start();
        JUCUtils.sysout("t1 -> unpark");
        LockSupport.unpark(t1);
        JUCUtils.sysout("t1 -> unpark completed");
//        JUCUtils.sleeper(1000);
    }
}
