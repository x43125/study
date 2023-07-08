package com.wx.base.claload;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wangxiang
 * @date 2023/7/7 16:40
 * @description
 */
public class ClassStudy03 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class c = ClassStudy03.class;
        System.out.println("c = " + c);
        ClassStudy03 o = (ClassStudy03) c.getConstructor(String.class).newInstance("zhangsan");
        o.print();
        Method print = c.getDeclaredMethod("print");
        print.invoke(o);

        Field name = c.getDeclaredField("name");
        name.setAccessible(true);
        Object o1 = name.get(o);
        System.out.println("name = " + o1);
    }

    private String name;

    public ClassStudy03(String name) {
        this.name = name;
    }

    private void print() {
        System.out.println("name: " + name);
    }
}
