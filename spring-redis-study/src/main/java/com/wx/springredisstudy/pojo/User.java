package com.wx.springredisstudy.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/04/14
 */
@Component
public class User implements Serializable {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
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
}
