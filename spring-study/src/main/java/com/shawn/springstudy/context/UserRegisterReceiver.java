package com.shawn.springstudy.context;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author wangxiang
 * @date 2023/6/28 14:50
 * @description
 */
@Component
public class UserRegisterReceiver {

    @EventListener
    public void userRegisterReceive(UserRegisteredEvent event) {
        System.out.println("userRegisterReceived the msg ========================" + event);
    }
}
