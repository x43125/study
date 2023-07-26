package com.zf.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shawn
 * @date 2023/7/26 14:09
 * @description
 */
@RestController
public class GoodController {
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("good")
    public String sayGood() {
        System.out.println("good!!!!!!!!!!!");
        return "good!!! " + serverPort;
    }

}
