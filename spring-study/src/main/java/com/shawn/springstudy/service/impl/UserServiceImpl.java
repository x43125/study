package com.shawn.springstudy.service.impl;

import com.shawn.springstudy.dao.UserDao;
import com.shawn.springstudy.model.User;
import com.shawn.springstudy.service.UserService;

import java.util.List;

/**
 * @author wangxiang
 * @date 2023/6/25 14:06
 * @description
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl() {
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findUserList() {
        return userDao.findUserList();
    }
}
