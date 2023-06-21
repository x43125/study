package com.shawn.springstudy.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangxiang
 * @date 2023/6/21 19:59
 * @description
 */
@RestController("/hello")
public class HelloController {

    @RequestMapping("/world")
    public String hello() {
        System.out.println("hello world");
        return "hello world";
    }
}
