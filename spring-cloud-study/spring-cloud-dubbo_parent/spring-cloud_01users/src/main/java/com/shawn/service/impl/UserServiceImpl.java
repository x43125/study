package com.shawn.service.impl;

import com.shawn.beans.UserAddress;
import com.shawn.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shawn
 * @date 2023/7/28 12:34
 * @description
 */
@DubboService
public class UserServiceImpl implements UserService {
    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        ArrayList<UserAddress> userAddresses = new ArrayList<>();
        UserAddress userAddress = new UserAddress();
        userAddress.setId(Long.parseLong(userId));
        userAddress.setUsername("Zhangsan");
        userAddress.setAddress("shanghaishi");
        userAddresses.add(userAddress);

        return userAddresses;
    }
}
