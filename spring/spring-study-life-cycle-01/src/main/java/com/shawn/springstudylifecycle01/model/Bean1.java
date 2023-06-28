package com.shawn.springstudylifecycle01.model;

import com.shawn.springstudylifecycle01.DigInAutowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author wangxiang
 * @date 2023/6/28 22:15
 * @description
 */
@Component
public class Bean1 {
    @Autowired
    private Bean2 bean2;

    private Bean3 bean3;

    @Resource
    public void setBean3(Bean3 bean3) {
        this.bean3 = bean3;
    }

    private String value;

    @Autowired
    public void setValue(@Value("${SHELL}") String value) {
        this.value = value;
    }

    @PostConstruct
    public void init() {
        System.out.println("init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy");
    }

    @Override
    public String toString() {
        return "Bean1{" +
                "bean2=" + bean2 +
                ", bean3=" + bean3 +
                ", value='" + value + '\'' +
                '}';
    }
}
