package com.wx.base;

import org.junit.Test;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/29
 */
public class GetClassTest {

    static class TargetClass {
        public void sout() {
            System.out.println("test");
        }
    }

    @Test
    public void test() {
        try {
            Class<TargetClass> targetClass1 = TargetClass.class;
            TargetClass targetClass11 = targetClass1.newInstance();
            targetClass11.sout();

            TargetClass t = new TargetClass();
            Class<? extends TargetClass> targetClass2 = t.getClass();
            TargetClass targetClass22 = targetClass2.newInstance();
            targetClass22.sout();

            Class<?> targetClass3 = Class.forName("com.wx.base.GetClassTest$TargetClass");
            TargetClass targetClass33 = (TargetClass) targetClass3.newInstance();
            targetClass33.sout();

            Class<?> targetClass4 = ClassLoader.getSystemClassLoader().loadClass("com.wx.base.GetClassTest$TargetClass");
            TargetClass targetClass44 = (TargetClass) targetClass4.newInstance();
            targetClass44.sout();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
