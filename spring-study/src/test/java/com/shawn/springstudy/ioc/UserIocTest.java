package com.shawn.springstudy.ioc;

import com.shawn.springstudy.config.UserIocConfig;
import com.shawn.springstudy.dao.UserDao;
import com.shawn.springstudy.dao.impl.UserDaoImpl;
import com.shawn.springstudy.model.User;
import com.shawn.springstudy.service.AgentService;
import com.shawn.springstudy.service.UserService;
import com.shawn.springstudy.service.impl.AgentServiceImpl;
import com.shawn.springstudy.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author wangxiang
 * @date 2023/6/25 14:11
 * @description
 */
@SpringBootTest
public class UserIocTest {

    @Test
    void testUserNormal() {
        UserServiceImpl userService = new UserServiceImpl();
        UserDao userDao = new UserDaoImpl();
        userService.setUserDao(userDao);
        List<User> userList = userService.findUserList();
        userList.forEach(System.out::println);
    }

    /**
     * xml配置
     */
    @Test
    void testUserIocByXmlConfig() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "com/shawn/ioc/UserDao.xml", "com/shawn/ioc/UserService.xml");
        UserService userService = context.getBean("userService", UserServiceImpl.class);
        List<User> userList = userService.findUserList();
        userList.forEach(System.out::println);
    }

    /**
     * Java配置类
     */
    @Test
    void testUserIocByJavaConfig() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(UserIocConfig.class);
        UserServiceImpl userService = context.getBean("userService", UserServiceImpl.class);
        List<User> userList = userService.findUserList();
        userList.forEach(System.out::println);
    }

    @Test
    void testUserIocByAnnotation() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.shawn.springstudy");
        UserService userService = context.getBean("userService", UserServiceImpl.class);
        List<User> userList = userService.findUserList();
        userList.forEach(System.out::println);
    }

    @Test
    void testUserIocByAnnotation2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.shawn.springstudy");
        AgentService agentService = context.getBean("agentServiceImpl", AgentServiceImpl.class);
        String agentName = agentService.getAgentName();
        System.out.println("agentName = " + agentName);
    }
}
