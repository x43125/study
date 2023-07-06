package com.wx.base.juc.base;

/**
 * @author wangxiang
 * @date 2023/7/6 21:14
 * @description
 */
public class PrintABC {
    public static void main(String[] args) {
        WaitNotify wn = new WaitNotify(1, 5);
        new Thread(() -> wn.print("a", 1, 2)).start();
        new Thread(() -> wn.print("b", 2, 3)).start();
        new Thread(() -> wn.print("c", 3, 1)).start();
    }

}

class WaitNotify {
    /**
     * 当前flag，与传过来的flag做比较
     */
    private int flag;
    /**
     * 循环次数
     */
    private final int loopNumber;

    public WaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    public void print(String str, int waitFlag, int nextFlag) {
        // 锁this
        synchronized (this) {
            // 循环
            for (int i = 0; i < loopNumber; i++) {
                // 比较是否一致，如果不一致则wait释放锁，等待唤醒
                while (waitFlag != flag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // 如果一致，则输出，并将标志改成下一个标志，并唤醒其他两个线程
                System.out.print(str);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}