package com.zf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

//@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrixDashboard
@EnableApolloConfig
public class HystrixDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }
}