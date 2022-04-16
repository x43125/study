package com.wx.springsourcestudy.guigu.beanlifecycle;

import com.wx.springsourcestudy.guigu.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
@ComponentScan("com.wx.springsourcestudy.guigu.bean")
@Configuration
public class MainConfigOfLifeCycle {
    // 单实例bean容器关闭时自动调用bean.destroy
    // 多实例时需要自行调用
//    @Scope("prototype")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car getCar() {
        return new Car();
    }
}
