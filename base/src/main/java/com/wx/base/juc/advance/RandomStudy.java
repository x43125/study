package com.wx.base.juc.advance;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/21
 */
public class RandomStudy {
    public static void main(String[] args) {
//        Random random = new Random();
//
//        for (int i=0; i<10; ++i) {
//            System.out.println(random.nextInt(5));
//        }

        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

        for (int i = 0; i < 10; ++i) {
            System.out.println(threadLocalRandom.nextInt(5));
        }


    }
}
