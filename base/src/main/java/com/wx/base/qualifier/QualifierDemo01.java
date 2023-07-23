package com.wx.base.qualifier;

import com.wx.base.qualifier.pkg2.QualifierMain02;

/**
 * @author Shawn
 * @date 2023/7/23 18:04
 * @description
 */
public class QualifierDemo01 implements Cloneable {
    public int age;
    public QualifierMain02 main02;

    public QualifierDemo01(int age, QualifierMain02 main02) {
        this.age = age;
        this.main02 = main02;
    }

    protected void print() {
        System.out.println("this is a protected function");
    }

    void print2() {
        System.out.println("this is a default function");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
