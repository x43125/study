package com.zf.com.controller;

import com.zf.com.client.HystrixClient;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shawn
 * @date 2023/7/26 21:17
 * @description
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private HystrixClient hystrixClient;

    @RequestMapping("/name")
    public String getUserName(@RequestParam("id") Integer id) {
        String hystrixHello = hystrixClient.getHystrixHello(id);
        return "username: " + hystrixHello;
    }

}
