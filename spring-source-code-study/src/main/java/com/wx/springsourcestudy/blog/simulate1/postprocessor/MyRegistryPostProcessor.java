package com.wx.springsourcestudy.blog.simulate1.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/02
 */
public class MyRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    public MyRegistryPostProcessor() {
        super();
        System.out.println("这是 BeanDefinitionRegistryPostProcessor 的构造器");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("=== MyRegistryPostProcessor.postProcessBeanDefinitionRegistry: " + registry.getBeanDefinitionCount());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("=== MyRegistryPostProcessor.postProcessBeanFactory: " + beanFactory.getBeanDefinitionCount());
    }
}
