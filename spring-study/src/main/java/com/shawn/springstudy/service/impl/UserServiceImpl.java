package com.shawn.springstudy.service.impl;

import com.shawn.springstudy.dao.UserDao;
import com.shawn.springstudy.model.User;
import com.shawn.springstudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangxiang
 * @date 2023/6/25 14:06
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
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

    public UserDao getUserDao() {
        return userDao;
    }
}
