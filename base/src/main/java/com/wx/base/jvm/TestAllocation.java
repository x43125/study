package com.wx.base.jvm;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/10/03
 */
public class TestAllocation {
    private static final int _1Mb = 1024 * 1024;

    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }

    /**
     * -verbose:gc -XX: +UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1Mb];
        allocation2 = new byte[2 * _1Mb];
        allocation3 = new byte[2 * _1Mb];
        allocation4 = new byte[4 * _1Mb];
    }

    /**
     * -verbose:gc -XX: +UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation = new byte[4 * _1Mb];
    }
}
