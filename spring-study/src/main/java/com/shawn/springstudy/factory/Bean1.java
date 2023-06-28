package com.shawn.springstudy.factory;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wangxiang
 * @date 2023/6/28 15:20
 * @description
 */
public class Bean1 {
    @Autowired
    private Bean2 bean2;

    public Bean1() {
        System.out.println("construct bean1");
    }

    public Bean2 getBean2() {
        return bean2;
    }

    public void setBean2(Bean2 bean2) {
        this.bean2 = bean2;
    }
}
