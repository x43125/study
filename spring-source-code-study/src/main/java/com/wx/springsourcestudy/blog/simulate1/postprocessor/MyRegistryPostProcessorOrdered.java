package com.wx.springsourcestudy.blog.simulate1.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/02
 */
public class MyRegistryPostProcessorOrdered implements BeanDefinitionRegistryPostProcessor, Ordered {

    public MyRegistryPostProcessorOrdered() {
        super();
        System.out.println("这是 MyRegistryPostProcessorOrdered 的构造器");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("=== MyRegistryPostProcessorOrdered.postProcessBeanDefinitionRegistry: " + registry.getBeanDefinitionCount());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("=== MyRegistryPostProcessorOrdered.postProcessBeanFactory: " + beanFactory.getBeanDefinitionCount());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
