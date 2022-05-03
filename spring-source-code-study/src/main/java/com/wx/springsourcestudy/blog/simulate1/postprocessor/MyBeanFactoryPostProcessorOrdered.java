package com.wx.springsourcestudy.blog.simulate1.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

/**
 * @author x
 */
public class MyBeanFactoryPostProcessorOrdered implements BeanFactoryPostProcessor, Ordered {

    public MyBeanFactoryPostProcessorOrdered() {
        super();
        System.out.println("这是 MyBeanFactoryPostProcessorOrdered 实现类构造器！！");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessorOrdered 调用postProcessBeanFactory方法！！");
        BeanDefinition bd = arg0.getBeanDefinition("person");
        bd.getPropertyValues().addPropertyValue("phone", "110");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
