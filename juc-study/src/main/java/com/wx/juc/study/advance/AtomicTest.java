package com.wx.juc.study.advance;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/11/21
 */
public class AtomicTest {
    public static void main(String[] args) {
        AtomicLong l = new AtomicLong(1000L);

        l.incrementAndGet();
        l.decrementAndGet();
        l.getAndIncrement();

        Map<String, String> map = new ConcurrentHashMap<>();

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    }

}
