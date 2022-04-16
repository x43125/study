package com.wx.springsourcestudy;

import com.wx.springsourcestudy.guigu.bean.Role;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: x43125
 * @Date: 22/04/06
 */
public class XmlApplicationContextTest {
    @Test
    public void test01() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Role role = (Role) context.getBean("role1");
        System.out.println(role);

    }
}
