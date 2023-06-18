package com.wx.base.jvm;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/10/10
 */
public class TestTryCatch {
    public static int testTryCatch() {
        int m = 1;
        try {
            m++;
            String str = null;
            System.out.println(str.toString());
            return m;
        } catch (Exception e) {
            m--;
            return m;
        } finally {
            m = 1111;
//            return m;
        }
    }

    public static void main(String[] args) {
        System.out.println(testTryCatch());
    }
}
