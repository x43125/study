package com.wx.base;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/28
 */
public class ConstructorPrivate {
    private String name;
    private Integer age;

    /**
     * 可以被private修饰，使用场景：单例等
     */
    private ConstructorPrivate() {

    }

    private ConstructorPrivate(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public static ConstructorPrivate getInstance() {
        return new ConstructorPrivate();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        ConstructorPrivate c = new ConstructorPrivate();
        c.setName("zhangsan");
        System.out.println("c.getName() = " + c.getName());
    }

    @Override
    public boolean equals(Object obj) {
        ConstructorPrivate c = (ConstructorPrivate) obj;
        return this.name.equals(c.name);
    }
}
