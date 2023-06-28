package com.shawn.springstudy.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author wangxiang
 * @date 2023/6/28 15:19
 * @description
 */
@Configuration
public class BeanFactoryConfig {
    @Bean
    public Bean1 bean1() {
        return new Bean1();
    }

    @Bean
    public Bean2 bean2() {
        return new Bean2();
    }
}
