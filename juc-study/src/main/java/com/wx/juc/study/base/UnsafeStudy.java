package com.wx.juc.study.base;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/20
 */
public class UnsafeStudy {

    // 报错，不能通过”正规“渠道直接使用 unsafe
//    static final Unsafe unsafe = Unsafe.getUnsafe();
//
//    static final long stateOffset;
//
//    private volatile long state = 0;
//
//    static {
//        try {
//            stateOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("state"));
//        } catch (Exception e) {
//            System.out.println(e.getLocalizedMessage());
//            throw new Error(e);
//        }
//    }
//
//    public static void main(String[] args) {
//        UnsafeTest test = new UnsafeTest();
//        Boolean success = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
//        System.out.println(success);
//    }


    static final Unsafe unsafe;
    static final long stateOffset;

    private volatile long state = 0;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            stateOffset = unsafe.objectFieldOffset(UnsafeStudy.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        UnsafeStudy unsafeStudy = new UnsafeStudy();
        Boolean success = unsafe.compareAndSwapInt(unsafeStudy, stateOffset, 0, 1);
        System.out.println(success);
    }

}
