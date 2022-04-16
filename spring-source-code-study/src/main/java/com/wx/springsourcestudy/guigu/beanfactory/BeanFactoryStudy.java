//package com.wx.springsourcestudy.guigu.beanfactory;
//
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.beans.factory.support.AbstractBeanDefinition;
//import org.springframework.beans.factory.support.BeanDefinitionBuilder;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.context.annotation.AnnotationConfigUtils;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author x43125
// */
//public class BeanFactoryStudy {
//
//    public static void main(String[] args) {
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();
//        beanFactory.registerBeanDefinition("config", beanDefinition);
//
//        // 给beanFactory添加一些常用的后(post)处理器
//        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
//        // BeanFactoryPostProcessor （后处理器）主要功能：补充了一些bean定义
//        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach(
//                beanFactoryPostProcessor -> {
//                    beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
//                }
//        );
//
//
//        beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanFactory::addBeanPostProcessor);
//
//        // 打印解析得到的bean
//        for (String name : beanFactory.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }
//        beanFactory.preInstantiateSingletons();
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        System.out.println("Bean2: " + beanFactory.getBean(Bean1.class).getBean2());
//    }
//
//    @Configuration
//    static class Config {
//        @Bean
//        public Bean1 bean1() {
//            return new Bean1();
//        }
//
//        @Bean
//        public Bean2 bean2() {
//            return new Bean2();
//        }
//    }
//}
