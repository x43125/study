package com.wx.springsourcestudy.guigu.autowired;

import com.wx.springsourcestudy.guigu.applicationcontext.mapperscan.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
@Configuration
@ComponentScan("com.wx.springsourcestudy.guigu.applicationcontext.mapperscan")
public class AutowiredConfig {

    @Bean("userDao2")
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setLabel(2);
        return userDao;
    }
}
