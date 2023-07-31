package com.shawn.springstudy.scheduled;

import org.springframework.stereotype.Component;

/**
 * @author Shawn
 * @date 2023/7/30 00:34
 * @description
 */
@Component
public class ScheduledStudySon1 extends ScheduledStudy {

    @Override
    public void print() {
        System.out.println("children111 print");
    }
}
