package main.java.com.wx.juc.study.base;

/**
 * @Author: x43125
 * @Date: 22/03/02
 */
public class VolatileDemo {
    static int volatileFlag = 1;
//    static volatile int volatileFlag = 1;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.print(volatileFlag++ + " ")).start();
        }

        System.out.println("end");
    }
}
