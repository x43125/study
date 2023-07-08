package com.wx.base.type.pkg;

import java.io.IOException;

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


        Integer i1 = 124;
        Integer i2 = 124;
        Integer i3 = new Integer(124);

        System.out.println(i1 == i2);
        System.out.println(i1 == i3);

        Integer i4 = 128;
        Integer i5 = 128;
        Integer i6 = new Integer(128);

        System.out.println(i4 == i5);
        System.out.println(i5 == i6);

        Parent p = new Child();
        try {
            System.out.println("p.getLongParam() = " + p.getLongParam());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void a() {

    }

    public int a(int b) {
        return b;
    }

    private void a(int b, int a) {

    }

    private void a(String a, int b) {
    }

    private void a(int a, String b) {
    }
}

class Parent {
    public long getLongParam() throws IOException {
        return 1L;
    }
}

class Child extends Parent {
//    @Override
//    public long getLongParam() throws Exception {
//        return 1;
//    }


}
