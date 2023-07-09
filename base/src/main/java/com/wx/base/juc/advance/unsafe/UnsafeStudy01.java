package com.wx.base.juc.advance.unsafe;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author wangxiang
 * @date 2023/7/9 10:43
 * @description
 */
public class UnsafeStudy01 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        // 静态变量反射的时候不需要传具体对象，直接传null即可
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        System.out.println("unsafe = " + unsafe);

        long idOffset = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("id"));
        long nameOffset = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("name"));

        System.out.println("idOffset = " + idOffset);
        System.out.println("nameOffset = " + nameOffset);

        Teacher teacher = new Teacher();
        unsafe.compareAndSwapInt(teacher, idOffset, 0, 1);
        unsafe.compareAndSwapObject(teacher, nameOffset, null, "zhangsan");
        System.out.println("teacher = " + teacher);
    }
}

@Data
class Teacher {
    private int id;
    private String name;
}
