package com.wx.springsourcestudy.guigu.bean;

import com.wx.springsourcestudy.guigu.applicationcontext.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author x43125
 */
@Component
public class Component2 {

    @EventListener
    public void listener1(UserRegisteredEvent event) {
        System.out.println(event);
        System.out.println("发送短信...");
    }

    @EventListener
    public void listener2(UserRegisteredEvent event) {
        System.out.println(event);
        System.out.println("发送邮件...");
    }

}
