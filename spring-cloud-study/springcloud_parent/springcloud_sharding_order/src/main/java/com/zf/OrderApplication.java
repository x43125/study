package com.zf;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 订单服务启动类
 */
@SpringBootApplication(exclude = {
    org.apache.shardingsphere.spring.boot.ShardingSphereAutoConfiguration.class
})
@EnableEurekaClient
@EnableApolloConfig
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
