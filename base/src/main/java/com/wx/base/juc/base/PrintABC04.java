package com.wx.base.juc.base;

/**
 * @author Shawn
 * @date 2023/7/13 19:19
 * @description
 */
public class PrintABC04 {
    public static void main(String[] args) {
        PrintABCSelf printABC = new PrintABCSelf(10, 0);

        new Thread(() -> printABC.print("A", 0, 1)).start();
        new Thread(() -> printABC.print("B", 1, 2)).start();
        new Thread(() -> printABC.print("C", 2, 0)).start();
    }
}

class PrintABCSelf {
    private int loopNumber;
    private int flag;

    public PrintABCSelf(int loopNumber, int flag) {
        this.loopNumber = loopNumber;
        this.flag = flag;
    }

    public void print(String c, int nowFlag, int nextFlag) {
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this) {
                // 如果不等于当前flag，则wait
                while (flag != nowFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // 打印当前应该打印的字符
                System.out.print(c + " ");
                // 将flag设置成下一个要打印的标志
                flag = nextFlag;
                // 唤醒其他线程
                this.notifyAll();
            }
        }
    }
}
