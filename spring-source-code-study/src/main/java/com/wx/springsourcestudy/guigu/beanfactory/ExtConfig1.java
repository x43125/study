package com.wx.springsourcestudy.guigu.beanfactory;

import com.wx.springsourcestudy.guigu.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: x43125
 * @Date: 22/03/28
 */
@ComponentScan({"com.wx.springsourcestudy.guigu.beanfactory", "com.wx.springsourcestudy.guigu.beandefinition"})
@Configuration
public class ExtConfig1 {

    @Bean
    public Car car() {
        return new Car();
    }
}
