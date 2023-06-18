package com.wx.base.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author wangxiang
 * @date 2023/6/11 10:50
 * @description
 */
public class Study05Reflection {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> son = Class.forName("com.shawn.claload.Son");

        // 获得本类及父类的所有公共方法
        Method[] methods = son.getMethods();
        for (Method method : methods) {
            System.out.println("a: " + method);
        }

        // 获得本类的所有方法，包括私有方法，不会获得父类的方法
        Method[] declaredMethods = son.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("b: " + declaredMethod);
        }

        // 获取指定构造器
        Constructor constructor = son.getDeclaredConstructor(int.class);
        System.out.println("constructor = " + constructor);
        constructor = son.getConstructor(int.class);
        System.out.println("constructor = " + constructor);
    }
}
