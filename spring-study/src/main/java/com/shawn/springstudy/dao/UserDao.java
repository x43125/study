package com.shawn.springstudy.dao;

import com.shawn.springstudy.model.User;

import java.util.List;

/**
 * @author wangxiang
 * @date 2023/6/25 14:03
 * @description
 */
public interface UserDao {
    List<User> findUserList();
}
