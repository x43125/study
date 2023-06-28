package com.shawn.springstudy.model;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxiang
 * @date 2023/6/28 10:41
 * @description
 */
@Slf4j
public class Student {
    private String name;
    private int age;

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

    public void doInit() {
        log.info("execute Student#doInit");
    }

    public void doDestroy() {
        log.info("execute Student#doDestroy");
    }
}
