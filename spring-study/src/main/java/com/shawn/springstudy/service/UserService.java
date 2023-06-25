package com.shawn.springstudy.service;

import com.shawn.springstudy.model.User;

import java.util.List;

/**
 * @author wangxiang
 * @date 2023/6/25 14:05
 * @description
 */
public interface UserService {
    List<User> findUserList();
}
