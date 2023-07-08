package com.wx.base.claload;

import com.wx.base.model.Son;

/**
 * @author wangxiang
 * @date 2023/6/11 08:56
 * @description
 */
public class ClassLoadStudy02 {

    static {
        System.out.println("Main类被加载");
    }

    public static void main(String[] args) {
//        Father f = new Father();
//        Son s = new Son();
//        System.out.println(Father.b);
//        System.out.println(Father.B);
        System.out.println(Son.b);
//        System.out.println(Son.a);
//        System.out.println(Son.m);
//        System.out.println(Son.M);
    }
}


