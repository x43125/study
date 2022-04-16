package com.wx.springsourcestudy.guigu.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
public class Person {
    @Value("zhangsan")
    private String name;
    @Value("#{20 - 2}")
    private int age;
    @Value("${person.nickname}")
    private String nickName;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
