package com.wx.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/28
 */
public class EqualsAndDoubleEqual {
    public static void main(String[] args) {
        String s = new String("sss");
        String b = new String("sss");

        System.out.println(s == b);
        System.out.println(s.equals(b));

        Integer a = new Integer(1111);
        Integer c = new Integer(1111);

        System.out.println(a == c);
        System.out.println(a.equals(c));

        ConstructorPrivate c1 = ConstructorPrivate.getInstance();
        ConstructorPrivate c2 = ConstructorPrivate.getInstance();
        c1.setName("zhangsan");
        c2.setName("zhangsan");
        System.out.println(c1 == c2);
        System.out.println(c1.equals(c2));
    }
}
