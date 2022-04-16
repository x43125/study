package com.wx.springsourcestudy.guigu.factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
@Configuration
public class MainConfig3 {

    @Bean
    public ColorFactory colorFactoryBean() {
        return new ColorFactory();
    }
}
