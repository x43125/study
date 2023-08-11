package com.shawn.king.schedule;

import com.shawn.king.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Shawn
 * @date 2023/8/10 11:38
 * @description
 */
@Slf4j
@Component
public class CountClearSchedule {

    @Autowired
    private UserController userController;

    @Scheduled(initialDelay = 5000, fixedRate = 50000)
    public void clearCount() {
        if (userController.getSuccessCount() != 0) {
            userController.clearCount();
            log.info("clear success!!!");
        }
    }
}
