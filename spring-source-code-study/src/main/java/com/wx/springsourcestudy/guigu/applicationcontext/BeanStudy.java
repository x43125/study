package com.wx.springsourcestudy.guigu.applicationcontext;

import com.wx.springsourcestudy.guigu.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
public class BeanStudy {

    public static void main(String[] args) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // 获取环境上下文
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // 获取指定类的bean
        Person person = context.getBean(Person.class);
        // 获取context中所有的BeanDefinition的名字
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("name: " + name);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // 根据类型获取所有的bean
        String[] namesForType = context.getBeanNamesForType(Person.class);
        for (String name : namesForType) {
            System.out.println("name: "+  name);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(person);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Person person2 = (Person) context.getBean("person");
        System.out.println(person2);
        System.out.println(person2 == person);
    }
}
