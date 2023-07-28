package com.shawn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shawn
 * @date 2023/7/28 12:27
 * @description
 */
@RestController
@RequestMapping("users")
public class UsersController {

    @RequestMapping("hello")
    public String sayHello() {
        System.out.println("hello " + this.getClass().getName());
        return "hello " + this.getClass().getName();
    }
}
