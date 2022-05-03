
package com.wx.springsourcestudy.blog.simulate1.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.PriorityOrdered;

/**
 * @author x
 */
public class MyBeanFactoryPostProcessorPriority implements BeanFactoryPostProcessor, PriorityOrdered {

    public MyBeanFactoryPostProcessorPriority() {
        super();
        System.out.println("这是 MyBeanFactoryPostProcessorPriority 实现类构造器！！");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessorPriority 调用postProcessBeanFactory方法！！");
        BeanDefinition bd = arg0.getBeanDefinition("person");
        bd.getPropertyValues().addPropertyValue("phone", "110");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
