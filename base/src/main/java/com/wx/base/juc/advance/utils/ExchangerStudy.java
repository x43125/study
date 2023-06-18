package com.wx.base.juc.advance.utils;

import java.util.concurrent.Exchanger;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/04
 */
public class ExchangerStudy {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String a = "hello ";
            String b;
            try {
                b = exchanger.exchange(a);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(a + b);
        }).start();

        new Thread(() -> {
            String b = "world";
            String a;
            try {
                a = exchanger.exchange("world");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(a + b);
        }).start();

        new Thread(() -> {
            String a;
            try {
                a = exchanger.exchange("hhh");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + a);
        }, "hhh").start();

        new Thread(() -> {
            String a;
            try {
                a = exchanger.exchange("aaa");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + a);
        }, "aaa").start();
    }
}
