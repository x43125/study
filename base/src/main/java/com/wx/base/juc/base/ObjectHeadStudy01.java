package com.wx.base.juc.base;

import com.wx.base.juc.base.model.Dog;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author wangxiang
 * @date 2023/7/6 10:42
 * @description
 */
public class ObjectHeadStudy01 {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        Dog dog = new Dog();
        System.out.println(ClassLayout.parseInstance(dog).toPrintable());
    }
}
