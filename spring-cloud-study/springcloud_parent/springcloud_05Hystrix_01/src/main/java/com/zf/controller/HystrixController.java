package com.zf.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shawn
 * @date 2023/7/26 17:02
 * @description
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixController {
    @GetMapping("/hello")
    @HystrixCommand(fallbackMethod = "demoFallBack")
    public String sayHello(Integer id) {
        if (id <= 0) {
            throw new RuntimeException("simulate exception");
        }
        System.out.println("hello hystrix!!!!!! " + id);
        return "hello hystrix " + id;
    }

    public String demoFallBack() {
        return "当前活动过于繁忙，请稍后再试";
    }
}
