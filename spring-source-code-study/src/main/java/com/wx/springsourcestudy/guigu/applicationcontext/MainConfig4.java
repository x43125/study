package com.wx.springsourcestudy.guigu.applicationcontext;

import com.wx.springsourcestudy.guigu.bean.HelloWorld;
import com.wx.springsourcestudy.guigu.bean.HelloWorld2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: x43125
 * @Date: 22/04/06
 */
@Configuration
public class MainConfig4 {
    @Bean
    public HelloWorld getHelloWorld() {
        return new HelloWorld();
    }

    @Autowired
    private HelloWorld2 helloWorld2;

    public HelloWorld2 getHelloWorld2() {
        return helloWorld2;
    }

}
