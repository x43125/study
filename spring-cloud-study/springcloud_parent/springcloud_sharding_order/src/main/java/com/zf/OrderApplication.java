package com.zf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 订单服务启动类
 * 
 * 功能：
 * 1. 启用 Eureka 客户端注册
 * 2. 启用 Apollo 配置中心
 * 3. 支持配置动态刷新
 */
@SpringBootApplication
@EnableEurekaClient
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
