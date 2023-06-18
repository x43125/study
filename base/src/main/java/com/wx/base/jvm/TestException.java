package com.wx.base.jvm;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/09/17
 */

public class TestException {
    public static void main(String[] args) {
        TestThrow testThrow = new TestThrow();
//        try {
        testThrow.testThrow1();
//        } catch (Exception e) {
//            System.out.println("main");
//            throw new RuntimeException("除数异常:" + e.getMessage(), e);
//        }
        System.out.println("1111111111");
    }
}
