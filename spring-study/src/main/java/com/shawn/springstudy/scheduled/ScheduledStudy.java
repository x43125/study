package com.shawn.springstudy.scheduled;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Shawn
 * @date 2023/7/30 00:34
 * @description
 */
@Component
public abstract class ScheduledStudy {
    @Scheduled(fixedRate = 2000)
    public void scheduled() {
        print();
    }

    public void print() {
        System.out.println("parent print");
    }
}
