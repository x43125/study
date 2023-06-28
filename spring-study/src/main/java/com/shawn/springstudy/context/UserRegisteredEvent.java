package com.shawn.springstudy.context;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * @author wangxiang
 * @date 2023/6/28 14:48
 * @description
 */
public class UserRegisteredEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public UserRegisteredEvent(Object source) {
        super(source);
    }
}
