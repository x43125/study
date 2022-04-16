package com.wx.springsourcestudy.guigu.applicationcontext.mapperscan.service;

import com.wx.springsourcestudy.guigu.applicationcontext.mapperscan.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
@Service
public class UserService {
//    @Qualifier("userDao3")
    @Autowired(required = false)
    private UserDao userDao;

    public void print() {
        System.out.println(userDao);
    }

    @Override
    public String toString() {
        return "UserService{" +
                "userDao=" + userDao +
                '}';
    }
}
