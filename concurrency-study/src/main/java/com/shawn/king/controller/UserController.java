package com.shawn.king.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shawn
 * @date 2023/8/10 10:52
 * @description
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    int successCount = 0;
    int failedCount = 0;

    @RequestMapping("/login")
    public String login(Long userid, String password) {
        if (userid <= 10000 && "123456".equals(password)) {
            log.warn("login success: {}, {}", userid, successCount);
            successCount++;
            return "login success!!! " + successCount;
        }

        failedCount++;
        log.error("login failed: {}, {}", userid, failedCount);
        return "login failed: username or password incorrect " + failedCount;
    }

    public void clearCount() {
        this.successCount = 0;
        this.failedCount = 0;
    }

    public int getSuccessCount() {
        return this.successCount;
    }
}
