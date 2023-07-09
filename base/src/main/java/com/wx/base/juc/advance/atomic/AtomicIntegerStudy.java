package com.wx.base.juc.advance.atomic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/05
 */
public class AtomicIntegerStudy {
    public static void main(String[] args) {
        Account accountUnsafe = new AccountUnsafe(100000);
        Account.demo(accountUnsafe);

        Account accountCas = new AccountUnsafe(100000);
        Account.demo(accountCas);
    }
}

interface Account {
    Integer getBalance();

    void withdraw(Integer amount);

    /**
     * 测试方法
     *
     * @param account
     */
    static void demo(Account account) {
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            ts.add(new Thread(() -> account.withdraw(10)));
        }
        long start = System.nanoTime();
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        long end = System.nanoTime();
        System.out.println(account.getBalance() + " cost: " + (end - start) / 1000_000 + " ms");
    }
}

/**
 * 加锁方式
 */
class AccountUnsafe implements Account {

    private Integer balance;

    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        synchronized (this) {
            return this.balance;
        }
    }

    @Override
    public void withdraw(Integer amount) {
        synchronized (this) {
            this.balance -= amount;
        }
    }
}

class AccountCas implements Account {
    private AtomicInteger balance;

    public AccountCas(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        while (true) {
            // 获取余额的最新值
            int prev = balance.get();
            // 要修改的余额
            int next = prev - amount;
            // 真正修改
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}
