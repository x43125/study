package com.wx.springsourcestudy.guigu.value;

import com.wx.springsourcestudy.guigu.bean.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
public class ValueStudy {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config4.class);
        Person person = (Person) context.getBean("person");
        System.out.println(person);
        context.close();
    }
}
