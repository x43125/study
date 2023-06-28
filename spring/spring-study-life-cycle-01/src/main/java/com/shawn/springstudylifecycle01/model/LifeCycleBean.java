package com.shawn.springstudylifecycle01.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author wangxiang
 * @date 2023/6/28 17:21
 * @description
 */
@Component
public class LifeCycleBean {
    public LifeCycleBean() {
        System.out.println("构造");
    }

    @Autowired
    public void autowire(@Value("${SHELL}") String name) {
        System.out.println("注入：" + name);
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁");
    }
}
