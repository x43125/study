package com.wx.springsourcestudy.guigu.beanlifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
public class TestLifeCycle {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器创建完成...");
//        Object bean = context.getBean("getCar");
//        System.out.println(bean);
        context.close();

    }
}
