package com.wx.base.model;

/**
 * @author wangxiang
 * @date 2023/6/11 11:11
 * @description
 */
public class Father {
    public static int b = 2;

    static {
        System.out.println("父类被加载");
    }

    public static final int B = 10;

    public void getFatherName() {

    }

    private void getFatherName2() {

    }
}
