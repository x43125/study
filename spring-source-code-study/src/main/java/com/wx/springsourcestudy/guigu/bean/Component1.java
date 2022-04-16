package com.wx.springsourcestudy.guigu.bean;

import com.wx.springsourcestudy.guigu.applicationcontext.UserRegisteredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author x43125
 */
@Component
public class Component1 {

    @Autowired
    private ApplicationEventPublisher context;

    public void register() {
        System.out.println("新用户注册...");
        context.publishEvent(new UserRegisteredEvent(this));
        System.out.println("判断验证码...");
    }


}
