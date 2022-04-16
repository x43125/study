package com.wx.jvm;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/10/03
 */
public class TestTenuringThreshold {
    private static final int _1Mb = 1024 * 1024;

    public static void main(String[] args) {
//        testTenuringThreshold();
        testTenuringThreshold2();
    }

    /**
     * -verbose:gc -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     */
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1Mb / 4];

        allocation2 = new byte[4 * _1Mb];
        allocation3 = new byte[4 * _1Mb];
        allocation3 = null;
        allocation3 = new byte[4 * _1Mb];
    }

    /**
     * -verbose:gc -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
     */
    public static void testTenuringThreshold2() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        // allocation1+allocation2大于survivor空间一半
        allocation1 = new byte[_1Mb / 4];
        allocation2 = new byte[_1Mb / 4];
        allocation3 = new byte[4 * _1Mb];
        allocation4 = new byte[4 * _1Mb];
        allocation4 = null;
        allocation4 = new byte[4 * _1Mb];
    }
}
