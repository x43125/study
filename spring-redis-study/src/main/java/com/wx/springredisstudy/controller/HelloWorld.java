package com.wx.springredisstudy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/04/14
 */
@RestController
@RequestMapping("/hello")
public class HelloWorld {
    @RequestMapping("/world")
    public String sayHello() {
        return "Hello World";
    }
}
