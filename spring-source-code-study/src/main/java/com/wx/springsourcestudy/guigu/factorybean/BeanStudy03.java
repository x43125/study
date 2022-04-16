package com.wx.springsourcestudy.guigu.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
public class BeanStudy03 {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig3.class);

        Object bean = context.getBean("colorFactoryBean");
        System.out.println(bean + " : " + bean.getClass());

    }
}
