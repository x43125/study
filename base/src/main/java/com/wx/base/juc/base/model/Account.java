package com.wx.base.juc.base.model;

/**
 * @author wangxiang
 * @date 2023/7/6 00:24
 * @description
 */
public class Account {
    private int money;

    public Account(int money) {
        this.money = money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return this.money;
    }

    public void transfer(Account target, int money) {
        // 锁在this上是不行的，因为此处的代码中既包含了this的又包含了target的修改
        // 只加载this上智能保证this.setMoney有问题，而无法保证target.setMoney
//            synchronized (this) {
//        synchronized (Account.class) {
            if (this.getMoney() >= money) {
                this.setMoney(this.getMoney() - money);
                target.setMoney(target.getMoney() + money);
            }
//        }
    }
}
