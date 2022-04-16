package com.wx.juc.study.advance;

import java.util.concurrent.locks.LockSupport;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/21
 */
public class ParkTest {

    public void parkTest() {
        LockSupport.park();
    }

    public static void main(String[] args) {
        ParkTest parkTest = new ParkTest();
        parkTest.parkTest();
    }
}
