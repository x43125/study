package com.wx.base.reflect;

import com.wx.base.model.Son;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wangxiang
 * @date 2023/6/11 11:08
 * @description
 */
public class Study06ReflectConstruct {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // Class.forName
        Class<?> son = Class.forName("com.shawn.base.model.Son");
        // 构造器
        Constructor<?> constructor = son.getDeclaredConstructor(int.class, String.class, int.class);
        Son zhangsan = (Son)constructor.newInstance(1, "zhangsan", 18);
        System.out.println("zhangsan = " + zhangsan);
        // 方法
        Method method = son.getDeclaredMethod("setName", String.class);
        method.invoke(zhangsan, "lisi");
        System.out.println("zhangsan = " + zhangsan);
        // 属性
        Field name = son.getDeclaredField("name");
        // 关闭私有属性的安全检测
        name.setAccessible(true);
        name.set(zhangsan, "wanger");
        System.out.println("zhangsan = " + zhangsan);
    }
}
