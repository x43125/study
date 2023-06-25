package com.shawn.springstudy.config;

import com.shawn.springstudy.dao.impl.UserDaoImpl;
import com.shawn.springstudy.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangxiang
 * @date 2023/6/25 14:22
 * @description
 */
@Configuration
public class UserIocConfig {
    /**
     * @return user dao
     */
    @Bean("userDao")
    public UserDaoImpl userDao() {
        return new UserDaoImpl();
    }

    /**
     * @return user service
     */
    @Bean("userService")
    public UserServiceImpl userService() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(userDao());
        return userService;
    }
}
