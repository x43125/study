package com.shawn.springstudy.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author wangxiang
 * @date 2023/6/25 14:03
 * @description
 */
@Slf4j
public class User implements BeanFactoryAware, BeanNameAware, ApplicationContextAware,
        InitializingBean, DisposableBean {
    /**
     * user's name.
     */
    private String name;

    /**
     * user's age.
     */
    private int age;

    /**
     * bean factory.
     */
    private BeanFactory beanFactory;

    /**
     * application context.
     */
    private ApplicationContext applicationContext;

    /**
     * bean name.
     */
    private String beanName;

    public User() {
        log.info("------------------------- execute User#new User()");
    }

    public void setName(String name) {
        log.info("------------------------- execute User#setName({})", name);
        this.name = name;
    }

    public void setAge(int age) {
        log.info("------------------------- execute User#setAge({})", age);
        this.age = age;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("------------------------- execute BeanFactoryAware#setBeanFactory");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String s) {
        log.info("------------------------- execute BeanNameAware#setBeanName");
        this.beanName = s;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("------------------------- execute ApplicationContextAware#setApplicationContext");
        this.applicationContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        log.info("------------------------- execute DisposableBean#destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("------------------------- execute InitializingBean#afterPropertiesSet");
    }


    public void doInit() {
        log.info("------------------------- execute User#doInit");
    }

    public void doDestroy() {
        log.info("------------------------- execute User#doDestroy");
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", beanFactory=" + beanFactory +
                ", applicationContext=" + applicationContext +
                ", beanName='" + beanName + '\'' +
                '}';
    }
}
