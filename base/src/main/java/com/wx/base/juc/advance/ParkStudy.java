package com.wx.base.juc.advance;

import java.util.concurrent.locks.LockSupport;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/21
 */
public class ParkStudy {

    public void parkTest() {
        LockSupport.park();
    }

    public static void main(String[] args) {
        ParkStudy parkStudy = new ParkStudy();
        parkStudy.parkTest();
    }
}
