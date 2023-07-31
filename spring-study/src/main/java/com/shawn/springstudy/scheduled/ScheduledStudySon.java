package com.shawn.springstudy.scheduled;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Shawn
 * @date 2023/7/30 00:34
 * @description
 */
@EnableScheduling
@Component
public class ScheduledStudySon extends ScheduledStudy {

    @Override
    public void print() {
        System.out.println("children print");
    }
}
