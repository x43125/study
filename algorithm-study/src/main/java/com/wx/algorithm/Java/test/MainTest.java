package com.wx.algorithm.Java.test;

public class MainTest {
    public static void main(String[] args) {
        String a = new String("ab"); // a 为一个引用
        String b = new String("ab"); // b为另一个引用,对象的内容一样
        String aa = "ab"; // 放在常量池中
        String bb = "ab"; // 从常量池中查找
        System.out.println(aa == bb);// true
        System.out.println(a == b);// false
        System.out.println(a.equals(b));// true
        System.out.println(42 == 42.0);// true

        System.out.println("5" + 2);

        Integer i1 = new Integer(10);
        Integer i2 = new Integer(10);
        System.out.println(i1 == i2);
        System.out.println(i1.equals(i2));

        Integer i3 = 10;
        Integer i4 = 10;
        System.out.println(i3 == i4);
        System.out.println(i1 == i3);
        System.out.println(i3.equals(i4));

        int i5 = 10;
        int i6 = 10;
        System.out.println(i5 == i6);
        System.out.println(i1 == i6);
        System.out.println(i3 == i6);
        
        System.out.println("================================");
        Integer i7 = 222;
        Integer i8 = new Integer(222);
        int i9 = 222;
        System.out.println(i7 == i8);
        System.out.println(i8 == i9);
        System.out.println(i7 == i9);
        

        String c = "5";
        String d = "52";
        
        System.out.println();

    }

}
