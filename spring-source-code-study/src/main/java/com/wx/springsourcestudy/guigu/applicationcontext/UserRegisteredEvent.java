package com.wx.springsourcestudy.guigu.applicationcontext;

import org.springframework.context.ApplicationEvent;

/**
 * @author x43125
 */
public class UserRegisteredEvent extends ApplicationEvent {

    public UserRegisteredEvent(Object source) {
        super(source);
    }
}
