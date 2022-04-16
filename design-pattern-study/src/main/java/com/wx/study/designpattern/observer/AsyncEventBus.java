package com.wx.study.designpattern.observer;

import java.util.concurrent.Executor;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/12/08
 */
public class AsyncEventBus extends EventBus {

    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}
