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
    static AtomicLong l = new AtomicLong(1000L);
    public static void main(String[] args) {
        System.out.println(l);

        new Thread(new AtomicSimulate()).start();
        new Thread(new AtomicSimulate()).start();
        new Thread(new AtomicSimulate()).start();
        new Thread(new AtomicSimulate()).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(l);

    }

    static class AtomicSimulate implements Runnable{
        @Override
        public void run() {
            l.incrementAndGet();
            System.out.println(Thread.currentThread() + " : " + l);
        }
    }

}
