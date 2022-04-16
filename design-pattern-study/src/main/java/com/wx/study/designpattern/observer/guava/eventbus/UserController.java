package com.wx.study.designpattern.observer.guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/12/08
 */

public class UserController {
//    private UserService userService; // 依赖注入
//
//    private EventBus eventBus;
//    private static final int DEFAULT_EVENTBUS_THREAD_POOL_SIZE = 20;
//
//    public UserController() {
//        //eventBus = new EventBus(); // 同步阻塞模式
//        eventBus = new AsyncEventBus(Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_POOL_SIZE)); // 异步非阻塞模式
//    }
//
//    public void setRegObservers(List<Object> observers) {
//        for (Object observer : observers) {
//            eventBus.register(observer);
//        }
//    }
//
//    public Long register(String telephone, String password) {
//        //省略输入参数的校验代码
//        //省略userService.register()异常的try-catch代码
//        long userId = userService.register(telephone, password);
//        eventBus.post(userId);
//        return userId;
//    }
//}
//
//public class RegPromotionObserver {
//    private PromotionService promotionService; // 依赖注入
//
//    @Subscribe
//    public void handleRegSuccess(Long userId) {
//        promotionService.issueNewUserExperienceCash(userId);
//    }
//}
//
//public class RegNotificationObserver {
//    private NotificationService notificationService;
//
//    @Subscribe
//    public void handleRegSuccess(Long userId) {
//        notificationService.sendInboxMessage(userId, "...");
//    }
}