package com.shawn.springstudy.dao.impl;

import com.shawn.springstudy.dao.UserDao;
import com.shawn.springstudy.model.User;

import java.util.Collections;
import java.util.List;

/**
 * @author wangxiang
 * @date 2023/6/25 14:04
 * @description
 */
public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findUserList() {
        return Collections.singletonList(new User(1L, "zhangsan", 11));
    }
}
