package com.wx.base.qualifier;

import com.wx.base.qualifier.pkg2.QualifierMain02;

/**
 * @author Shawn
 * @date 2023/7/23 18:05
 * @description
 */
public class QualifierMain01 {
    public static void main(String[] args) {
        QualifierDemo01 demo01 = new QualifierDemo01(11, new QualifierMain02("zhangsan"));
        demo01.print();
        demo01.print2();
        System.out.println("demo01.hashCode() = " + demo01.hashCode());

        try {
            QualifierDemo01 clone = (QualifierDemo01) demo01.clone();
            demo01.age++;
            System.out.println("demo01.age = " + demo01.age);
            System.out.println("clone.age = " + clone.age);

            demo01.main02.name = "lisi";
            System.out.println("demo01.main02.name = " + demo01.main02.name);
            System.out.println("clone.main02.name = " + clone.main02.name);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
