package com.wx.base.juc.base;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangxiang
 * @date 2023/7/6 20:30
 * @description
 */
public class ReentrantLockStudy02 {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");
        new Philosopher("1阿基米德", c1, c2).start();
        new Philosopher("2亚里士多德", c2, c3).start();
        new Philosopher("3黑格尔", c3, c4).start();
        new Philosopher("4歌德", c4, c5).start();
        new Philosopher("5柏拉图", c5, c1).start();
    }
}

class Philosopher extends Thread {
    private Chopstick left;
    private Chopstick right;
    private String name;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.name = name;
        this.left = left;
        this.right = right;
    }

//    @Override
//    public void run() {
//        while (true) {
//            // 此处会有问题，容易产生死锁
//            // 尝试获取左手筷子
//            synchronized (left) {
//                // 尝试获取右手筷子
//                synchronized (right) {
//                    eat();
//                }
//            }
//        }
//    }

    /**
     * 优化
     */
    @Override
    public void run() {
        while (true) {
            // 此处会有问题，容易产生死锁
            // 尝试获取左手筷子
            if (left.tryLock()) {
                try {
                    // 尝试获取右手筷子
                    if (right.tryLock()) {
                        try {
                            eat();
                        } finally {
                            right.unlock();
                        }
                    }
                    // 如果获取右手的锁失败了，会释放左手的锁
                } finally {
                    left.unlock();
                }
            }
        }
    }

    private void eat() {
        try {
            System.out.println(this.getName() + ": eating");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Chopstick extends ReentrantLock {
    private String name;

    public Chopstick(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
