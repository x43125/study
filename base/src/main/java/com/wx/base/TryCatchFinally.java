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

    public static int returnI() {
        int a = 1;
        try {
            int i = 1 / 0;
            return a;
        } catch (Exception e) {
//            e.printStackTrace();
            a++;
            return a + 1;
        } finally {
            return a + 2;
        }
    }
}
