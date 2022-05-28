package com.wx.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/28
 */
public class DataStructure {
    public static void main(String[] args) {
        Integer i1 = 33;
        Integer i2 = 33;

        Integer i3 = i1 + 1;
        Integer i4 = i2 + 1;

        Integer i5 = new Integer(33);
        Integer i6 = new Integer(33);


        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
        System.out.println(i5 == i6);

        Long l1 = 128L;
        Long l2 = 128L;

        Long l3 = 127L;
        Long l4 = 127L;

        long l5 = l3;
        long l6 = l4;

        System.out.println(l1 == l2);
        System.out.println(l3 == l4);

        System.out.println(l5 == l6);
    }
}
