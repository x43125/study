package com.wx.base.juc.base;

import com.wx.base.juc.base.model.Account;

import java.util.Random;

/**
 * 转账业务
 *
 * @author wangxiang
 * @date 2023/7/6 00:10
 * @description
 */
public class SynchronizedExm01 {
    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(1000);
        Account b = new Account(1000);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a.transfer(b, randomAmount());
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                b.transfer(a, randomAmount());
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("余额 a = " + a.getMoney() + " b = " + b.getMoney() + " 共计 = " + (a.getMoney() + b.getMoney()));
    }

    static Random random = new Random();

    public static int randomAmount() {
        return random.nextInt(100) + 1;
    }
}
