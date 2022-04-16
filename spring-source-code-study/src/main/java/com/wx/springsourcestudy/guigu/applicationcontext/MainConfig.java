package com.wx.springsourcestudy.guigu.applicationcontext;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
// 告诉spring这是一个配置类
@Configuration
// 包扫描，将指定路径下的包扫描注册到容器中
@ComponentScan(value = "com.wx.springsourcestudy.guigu.applicationcontext.mapperscan",
        // 排除掉某些规则的类
        excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})
        })
public class MainConfig {
//    @Lazy
//    @Scope(value = "prototype")
//    @Bean("person")
//    public Person getPerson() {
//        System.out.println("将 person 加进容器...");
//        return new Person();
//    }
}
