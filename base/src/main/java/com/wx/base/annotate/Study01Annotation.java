package com.wx.base.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangxiang
 * @date 2023/6/10 10:31
 * @description
 */
@MyAnnotation
@SuppressWarnings("unchecked")
public class Study01Annotation {
    public static void main(String[] args) {
        System.out.println("Hello annotation");
        /*
        内置注解：常用
            - @Override: 重写父类方法的时候要加，不加也可以
            - @Deprecated：废弃，不建议使用，或存在更好的方式，但可以使用
            - @SuppressWarnings：镇压警告，即使用该注解的区域下不报警告
         */
        /*
        元注解：meta-annotation
            - @Target: 用于描述注解的使用范围; ElementType.*
            - @Retention: 表示需要在什么级别保存该注释信息，用于描述注解的生命周期; SOURCE < CLASS < RUNTIME
            - @Document: 说明该注解将被包含在javadoc中
            - @Inherited: 说明子类可以继承父类中的该注解
         */
        /*
        声明方式：@interface
        参数：如果只有一个参数一般命名为 value 如下例MyAnnotation2，只有一个参数的时候，如果是value，则value=可以省略，如果是其他的不可省略
            可以给一个默认值，有默认值的参数使用的时候可以不赋值，否则不行
         */

    }

    @MyAnnotation3(age = 18)
    @MyAnnotation2(age = 11)
    @MyAnnotation
    public void testAnnotation() {

    }
}

@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation {

}

@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation2 {
    //    String value();
    int age();
}

@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation3 {
    String name() default "";
    int age() default -1;
}