package com.wx.base.juc.advance.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/05
 */
public class AtomicIntegerStudy {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        System.out.println(atomicInteger.incrementAndGet());
    }
}
