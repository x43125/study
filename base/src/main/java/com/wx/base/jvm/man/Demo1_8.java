package com.wx.base.jvm.man;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @author Shawn
 * @date 2023/7/28 22:28
 * @description 方法区溢出的实例
 */
// 继承自ClassLoader从而得到加载类的功能
public class Demo1_8 extends ClassLoader {
    public static void main(String[] args) {
        int j = 0;
        try {
            Demo1_8 demo18 = new Demo1_8();
            // 循环加载10000个类
            for (int i = 0; i < 10000; i++, j++) {
                // 类生成器
                ClassWriter cw = new ClassWriter(0);
                // jdk版本，类访问修饰符，类名，包名，父类，接口
                cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class_" + i, null, "java/lang/Object", null);
                // 返回类二进制数据
                byte[] code = cw.toByteArray();
                // 加载类
                demo18.defineClass("Class_" + i, code, 0, code.length);
            }
        } finally {
            System.out.println(j);
        }
    }
}
