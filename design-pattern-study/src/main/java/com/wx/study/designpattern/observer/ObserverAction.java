package com.wx.study.designpattern.observer;

import jdk.internal.util.Preconditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Descrption: 用来解释 @Subscribe 注解的方法
 * @Author: x43125
 * @Date: 21/12/08
 */
public class ObserverAction {
    private Object target;
    private Method method;

    public ObserverAction(Object target, Method method) {
        if (target == null) {
            throw new RuntimeException("target is null");
        }
        this.target = target;
        this.method = method;
        this.method.setAccessible(true);
    }

    public void execute(Object event) {
        try {
            method.invoke(target, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
