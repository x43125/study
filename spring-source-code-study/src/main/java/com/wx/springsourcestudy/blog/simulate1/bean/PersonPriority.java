package com.wx.springsourcestudy.blog.simulate1.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.core.PriorityOrdered;

import javax.annotation.Priority;
import java.lang.annotation.Annotation;

/**
 * @author x
 */
public class PersonPriority implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean, PriorityOrdered {

    private String name;
    private String address;
    private int phone;

    private BeanFactory beanFactory;
    private String beanName;

    public PersonPriority() {
        System.out.println("【构造器】调用 PersonPriority 的构造器实例化");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("【注入属性】注入属性 PersonPriority.name");
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        System.out.println("【注入属性】注入属性 PersonPriority.address");
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        System.out.println("【注入属性】注入属性 PersonPriority.phone");
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PersonPriority [address=" + address + ", name=" + name + ", phone="
                + phone + "]";
    }

    // 这是BeanFactoryAware接口方法
    @Override
    public void setBeanFactory(BeanFactory arg0) throws BeansException {
        System.out.println("PersonPriority:【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
        this.beanFactory = arg0;
    }

    // 这是BeanNameAware接口方法
    @Override
    public void setBeanName(String arg0) {
        System.out.println("PersonPriority:【BeanNameAware接口】调用BeanNameAware.setBeanName()");
        this.beanName = arg0;
    }

    // 这是InitializingBean接口方法
    @Override
    public void afterPropertiesSet() {
        System.out.println("PersonPriority:【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }

    // 这是DisposableBean接口方法
    @Override
    public void destroy() {
        System.out.println("PersonPriority:【DisposableBean接口】调用DisposableBean.destroy()");
    }

    // 通过<bean>的init-method属性指定的初始化方法
    public void myInit() {
        System.out.println("PersonPriority:【init-method】调用<bean>的init-method属性指定的初始化方法");
    }

    // 通过<bean>的destroy-method属性指定的初始化方法
    public void myDestroy() {
        System.out.println("PersonPriority:【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
