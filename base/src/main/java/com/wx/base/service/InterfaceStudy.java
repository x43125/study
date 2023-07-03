package com.wx.base.service;

/**
 * @author wangxiang
 * @date 2023/7/2 11:34
 * @description
 */
public interface InterfaceStudy {

    int x = 123;
    public int y = 0;

    default void func1() {
        System.out.println("this is func1, it implement itself");
    }

    void func2();
}
