package com.shawn.service.impl;

import com.shawn.beans.UserAddress;
import com.shawn.service.OrderService;
import com.shawn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Shawn
 * @date 2023/7/28 12:34
 * @description
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserService userService;

    @Override
    public List<UserAddress> initOrder(String userId) {
        List<UserAddress> userAddressList = userService.getUserAddressList(userId);
        System.out.println(userAddressList);
        return userAddressList;
    }
}
