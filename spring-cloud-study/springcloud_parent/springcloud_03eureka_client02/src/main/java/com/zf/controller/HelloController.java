package com.zf.controller;

import com.zf.client.GoodFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shawn
 * @date 2023/7/26 09:21
 * @description
 */
@RestController
public class HelloController {

    @Autowired
    private GoodFeignClient goodFeignClient;

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello word";
    }

    @RequestMapping("/test/feign")
    public String testFeign() {
        System.out.println("使用feign调用 goodController 成功");
        return goodFeignClient.sayGood();
    }

}
