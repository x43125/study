package com.wx.springsourcestudy.blog.simulate1.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.PriorityOrdered;

import javax.annotation.Priority;
import java.lang.annotation.Annotation;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/02
 */
public class MyRegistryPostProcessorPriority implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

    public MyRegistryPostProcessorPriority() {
        super();
        System.out.println("这是 MyRegistryPostProcessorPriority 的构造器");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("=== MyRegistryPostProcessorPriority.postProcessBeanDefinitionRegistry: " + registry.getBeanDefinitionCount());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("=== MyRegistryPostProcessorPriority.postProcessBeanFactory: " + beanFactory.getBeanDefinitionCount());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
