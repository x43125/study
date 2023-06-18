package com.wx.base.reflect;

import com.shawn.base.model.Son;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wangxiang
 * @date 2023/6/11 11:29
 * @description
 */
public class Study07Efficiency {
    public static void main(String[] args) throws Exception {
        test01();
        test02();
        test03();
    }

    public static void test01() {
        Son son = new Son();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 2000000000; i++) {
            son.getName();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    public static void test02() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> sonC = Class.forName("com.shawn.base.model.Son");
        Son son = (Son) sonC.newInstance();

        Method getName = sonC.getMethod("getName", null);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 2000000000; i++) {
            getName.invoke(son, null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    public static void test03() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> sonC = Class.forName("com.shawn.base.model.Son");
        Son son = (Son) sonC.newInstance();

        Method getName = sonC.getMethod("getName", null);
        getName.setAccessible(true);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 2000000000; i++) {
            getName.invoke(son, null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}
