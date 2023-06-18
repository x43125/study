package com.wx.base.reflect;

/**
 * @author wangxiang
 * @date 2023/6/10 11:10
 * @description
 */
public class Study02Reflection {
    public static void main(String[] args) throws ClassNotFoundException {

        Class c1 = Class.forName("com.shawn.base.reflect.User");
        Class c2 = Class.forName("com.shawn.base.reflect.User");
        Class c3 = Class.forName("com.shawn.base.reflect.User");
        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());

        Class<?> a1 = Class.forName("com.shawn.base.reflect.Animal");
        Class<?> a2 = Class.forName("com.shawn.base.reflect.Animal");
        Class<?> a3 = Class.forName("com.shawn.base.reflect.Animal");

        System.out.println(a1.hashCode());
        System.out.println(a2.hashCode());
        System.out.println(a3.hashCode());
    }
}

class User {
    private String name;
    private int id;
    private int age;
}

class Animal {
    private int id;
    private String name;
}
