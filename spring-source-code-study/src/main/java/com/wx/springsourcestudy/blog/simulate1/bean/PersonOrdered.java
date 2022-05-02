package com.wx.springsourcestudy.blog.simulate1.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.core.Ordered;

/**
 * @author x
 */
public class PersonOrdered implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean, Ordered {

    private String name;
    private String address;
    private int phone;

    private BeanFactory beanFactory;
    private String beanName;

    public PersonOrdered() {
        System.out.println("PersonOrdered:【构造器】调用Person的构造器实例化");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("PersonOrdered:【注入属性】注入属性name");
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        System.out.println("PersonOrdered:【注入属性】注入属性address");
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        System.out.println("PersonOrdered:【注入属性】注入属性phone");
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PersonOrdered: [address=" + address + ", name=" + name + ", phone="
                + phone + "]";
    }

    // 这是BeanFactoryAware接口方法
    @Override
    public void setBeanFactory(BeanFactory arg0) throws BeansException {
        System.out.println("PersonOrdered:【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
        this.beanFactory = arg0;
    }

    // 这是BeanNameAware接口方法
    @Override
    public void setBeanName(String arg0) {
        System.out.println("PersonOrdered:【BeanNameAware接口】调用BeanNameAware.setBeanName()");
        this.beanName = arg0;
    }

    // 这是InitializingBean接口方法
    @Override
    public void afterPropertiesSet() {
        System.out.println("PersonOrdered:【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }

    // 这是DisposableBean接口方法
    @Override
    public void destroy() throws Exception {
        System.out.println("PersonOrdered:【DisposableBean接口】调用DisposableBean.destroy()");
    }

    // 通过<bean>的init-method属性指定的初始化方法
    public void myInit() {
        System.out.println("PersonOrdered:【init-method】调用<bean>的init-method属性指定的初始化方法");
    }

    // 通过<bean>的destroy-method属性指定的初始化方法
    public void myDestroy() {
        System.out.println("PersonOrdered:【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
