package com.wx.base.qualifier.pkg2;

import com.wx.base.qualifier.QualifierDemo01;

/**
 * @author Shawn
 * @date 2023/7/23 18:05
 * @description
 */
public class QualifierMain02 {
    public String name;

    public QualifierMain02(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        QualifierDemo01 demo01 = new QualifierDemo01(12, new QualifierMain02("zhangsan"));
//        demo01.print();

    }
}
