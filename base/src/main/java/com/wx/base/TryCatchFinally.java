package com.wx.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/28
 */
public class TryCatchFinally {

    public static void main(String[] args) {
        System.out.println("returnI() = " + returnI());
    }

    public static String returnI() {
        try {
            int i = 1 / 0;
            return "try";
        } catch (Exception e) {
//            e.printStackTrace();
            throw new Exception();
//            return "catch";
        } finally {
            return "finally";
        }
//        return res + 3;
    }
}
