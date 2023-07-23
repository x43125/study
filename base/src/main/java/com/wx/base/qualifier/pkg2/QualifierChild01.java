package com.wx.base.qualifier.pkg2;

import com.wx.base.qualifier.QualifierDemo01;

/**
 * @author Shawn
 * @date 2023/7/23 18:06
 * @description
 */
public class QualifierChild01 extends QualifierDemo01 {


    public QualifierChild01(int age, QualifierMain02 main02) {
        super(age, main02);
    }

    public void print03() {
        super.print();
    }
}
