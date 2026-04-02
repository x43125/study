package com.wx;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author: x43125
 * @Date: 22/04/02
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableApolloConfig
public class EurekaClientApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EurekaClientApplication.class);
    }
}
