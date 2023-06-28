package com.shawn.springstudy.ref;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author wangxiang
 * @date 2023/6/28 11:36
 * @description
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("========================== MyBeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry");
        // 自己的逻辑处理
        BeanDefinition user = registry.getBeanDefinition("user");
        System.out.println("========================== " + user);
        MutablePropertyValues propertyValues = user.getPropertyValues();
        propertyValues.forEach(System.out::println);
        propertyValues.addPropertyValue("name", "zhangsansansansansansansan");

        if (propertyValues.contains("name")) {
            propertyValues.removePropertyValue("name");
            propertyValues.addPropertyValue("name", "zhangsansansansansansansan");
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("========================== MyBeanDefinitionRegistryPostProcessor#postProcessBeanFactory");
        // 自己的逻辑处理
    }
}
