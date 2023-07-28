package com.shawn.service;

import com.shawn.beans.UserAddress;

import java.util.List;

/**
 * @author wangxiang
 * @date 2023/7/28 12:34
 * @description
 */
public interface OrderService {
    List<UserAddress> initOrder(String userId);
}
