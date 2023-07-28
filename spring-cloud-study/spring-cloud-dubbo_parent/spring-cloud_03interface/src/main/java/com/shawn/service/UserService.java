package com.shawn.service;

import com.shawn.beans.UserAddress;

import java.util.List;

/**
 * @author Shawn
 * @date 2023/7/28 12:34
 * @description
 */
public interface UserService {
    List<UserAddress> getUserAddressList(String userId);
}
