package com.wx.springsourcestudy.guigu.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
@Component
public class Dog {
    public Dog() {
        System.out.println("dog constructor...");
    }

    @PostConstruct
    public void init() {
        System.out.println("Dog @PostConstructor...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("dog destroy...");
    }
}
