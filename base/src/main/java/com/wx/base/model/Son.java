package com.wx.base.model;

/**
 * @author wangxiang
 * @date 2023/6/11 11:11
 * @description
 */
public class Son extends Father {

    private int id;
    private String name;
    private int age;

    public static int a = 1;

    static {
        System.out.println("子类被加载");
        m = 300;
    }

    public static int m = 100;

    public static final int M = 1;

    public Son() {}

    public Son(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void getName2() {

    }

    @Override
    public String toString() {
        return "Son{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
