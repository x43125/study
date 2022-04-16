package com.wx.springsourcestudy.guigu.applicationcontext;

import com.wx.springsourcestudy.guigu.bean.HelloWorld;
import com.wx.springsourcestudy.guigu.bean.HelloWorld2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: x43125
 * @Date: 22/04/06
 */
@Configuration
public class BeanStudy3 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        HelloWorld bean = context.getBean(HelloWorld.class);
        System.out.println("==============================");
        bean.sout();
        System.out.println("==============================");
        HelloWorld2 bean1 = context.getBean(HelloWorld2.class);
        bean1.sout();
        System.out.println("==============================");
    }
}
