package com.wx.base.type.pkg;

/**
 * @author wangxiang
 * @date 2023/6/29 17:07
 * @description
 */
public class BaseTypePackageStudy {
    public static void main(String[] args) {
        Integer x = Integer.valueOf(123);
        Integer z = Integer.valueOf(123);
        Integer y = new Integer(123);
        System.out.println(x.equals(y));
        System.out.println(x == y);
        System.out.println(x == z);

        Integer a = 123;
        Integer b = 123;
        System.out.println(a == b);

        a = a + 1;
        System.out.println(a);
        System.out.println(b);
        System.out.println(a == b);

        String s1 = "abc";
        String s2 = "abc";
        System.out.print(s1 == s2);
        System.out.println(s1.equals(s2));

        String s3 = new String("abc");
        String s4 = new String("abc");

        System.out.print(s3 == s4);
        System.out.println(s3.equals(s4));

        System.out.print(s1 == s3);
        System.out.println(s1.equals(s3));

        String s5 = "abc".intern();
        System.out.println(s1 == s5);
        System.out.println(s3 == s5);
    }
}
