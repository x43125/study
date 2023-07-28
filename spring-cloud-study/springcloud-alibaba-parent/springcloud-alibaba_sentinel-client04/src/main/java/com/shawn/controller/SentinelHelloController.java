package com.shawn.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shawn
 * @date 2023/7/27 13:38
 * @description
 */
@RestController
@RequestMapping("sentinel")
public class SentinelHelloController {
    @Value("${server.port}")
    private String port;
    @Value("${spring.application.name}")
    private String appName;

    @RequestMapping("/sayHello")
    public String sayHello() {
        System.out.println("hello");
        return "hello " + appName + " : " + port;
    }
}
