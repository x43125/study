package com.wx.springsourcestudy.guigu.autowired;

import com.wx.springsourcestudy.guigu.applicationcontext.mapperscan.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
public class AutowiredTest {
    @Test
    public void test01() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutowiredConfig.class);
        UserService userService = context.getBean(UserService.class);
//        UserDao userDao = context.getBean(UserDao.class);
//        System.out.println(userDao);
        System.out.println(userService);
    }
}
