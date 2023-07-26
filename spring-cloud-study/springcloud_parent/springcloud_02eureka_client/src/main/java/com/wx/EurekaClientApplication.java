package com.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

import java.util.Map;

/**
 * @Author: x43125
 * @Date: 22/04/02
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class EurekaClientApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EurekaClientApplication.class);
    }
}
