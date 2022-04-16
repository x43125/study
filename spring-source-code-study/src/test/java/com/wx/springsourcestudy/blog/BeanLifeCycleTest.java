package com.wx.springsourcestudy.blog;

import com.wx.springsourcestudy.blog.simulate1.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

/**
 * 模拟一个普通bean的过程
 */
public class BeanLifeCycleTest {
    @Test
    public void test01() {
        System.out.println("现在开始初始化容器！！！！！");
        ApplicationContext factory = new ClassPathXmlApplicationContext("beans/person.xml");
        System.out.println("容器初始化成功！！！！！");
        //得到Person，并使用
        Person person = factory.getBean("person", Person.class);
        System.out.println("person: " + person  + "！！！！！");
        System.out.println("现在开始关闭容器！！！！！");
        ((ClassPathXmlApplicationContext) factory).registerShutdownHook();
        System.out.println("容器已关闭！！！！！");
    }
}
