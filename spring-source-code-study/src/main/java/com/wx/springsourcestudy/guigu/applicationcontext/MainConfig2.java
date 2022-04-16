package com.wx.springsourcestudy.guigu.applicationcontext;

import com.wx.springsourcestudy.guigu.applicationcontext.condition.WindowsCondition;
import com.wx.springsourcestudy.guigu.applicationcontext.condition.LinuxCondition;
import com.wx.springsourcestudy.guigu.bean.Person;
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
public class MainConfig2 {
//    @Lazy
//    @Scope(value = "prototype")
    @Bean("person")
    public Person getPerson() {
        System.out.println("将 person 加进容器...");
        return new Person("lisi", 10);
    }

    // 按照条件注册bean
    @Conditional(WindowsCondition.class)
    @Bean("bill")
    public Person getBill() {
        return new Person("Bill Gates", 88);
    }

    @Conditional(LinuxCondition.class)
    @Bean("linus")
    public Person getLinus() {
        return new Person("Linus", 66);
    }
}
