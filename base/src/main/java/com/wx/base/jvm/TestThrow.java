package com.wx.base.jvm;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/09/17
 */
public class TestThrow {
    public void testThrow1() {
//        try {
        throwExc();
//        } catch (Exception e) {
//            System.out.println("testThrow1");
//            throw new RuntimeException("testThrow1: " + e.getMessage(), e);
//        }
    }

    private void throwExc() {
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            System.out.println("除0异常");
            throw new RuntimeException("除0异常: " + e.getMessage(), e);
        }
    }
}
