package com.wx.springsourcestudy.guigu.value;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
@PropertySource(value = "classpath:/person.properties", encoding = "utf-8")
@Configuration
public class Config4 {

//    @Bean("person")
//    public Person getPerson() {
//        return new Person();
//    }
}
