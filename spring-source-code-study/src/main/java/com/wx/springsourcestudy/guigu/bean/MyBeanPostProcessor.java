package com.wx.springsourcestudy.guigu.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    public MyBeanPostProcessor() {
        System.out.println("Constructor Of MyBeanPostProcessor.............................");
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("**postProcessBeforeInitialization => " + beanName + " => " + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("**postProcessAfterInitialization => " + beanName + " => " + bean);
        return bean;
    }
}
