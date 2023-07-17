package com.wx.base.juc.advance;

import com.wx.base.juc.advance.utils.JUCUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Descrption: 极客时间：ThreadLocal
 * @Author: x43125
 * @Date: 21/11/20
 */
public class ThreadLocalStudy04 {

    static final AtomicLong NEXT_ID = new AtomicLong(0);

    static final ThreadLocal<Long> T_1 = ThreadLocal.withInitial(() -> NEXT_ID.getAndDecrement());

    static long get() {
        return T_1.get();
    }

    public static void main(String[] args) {
        // 相同线程多次调用，结果都是相同的不会改变
//        long nextId = ThreadLocalStudy04.get();
//        JUCUtils.sysout("nextId = " + nextId);
//
//        long nextId3 = ThreadLocalStudy04.get();
//        JUCUtils.sysout("nextId = " + nextId3);
//
//        new Thread(() -> {
//            long nextId1 = ThreadLocalStudy04.get();
//            JUCUtils.sysout("nextId = " + nextId1);
//        }).start();
//
//        new Thread(() -> {
//            long nextId2 = ThreadLocalStudy04.get();
//            JUCUtils.sysout("nextId = " + nextId2);
//        }).start();

        // ThreadLocal来实现 线程安全的 DateFormat
//        new Thread(() -> {
//            DateFormat df = SafeDateFormat.get();
//            String time = df.format(new Date());
//            System.out.println("time = " + time + " " + df);
//        }).start();
//
//        new Thread(() -> {
//            DateFormat df = SafeDateFormat.get();
//            String time = df.format(new Date());
//            System.out.println("time = " + time + " " + df);
//        }).start();

//        DateFormat df = SafeDateFormat.get();
//        String time = df.format(new Date());
//        System.out.println("time = " + time + " " + df);

        // 线程吃模式：
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                try {
                    DateFormat df = SafeDateFormat.get();
                    String time = df.format(new Date());
                    JUCUtils.sysout(time);
                } finally {
                    SafeDateFormat.remove();
                }
            });
        }
    }
}

class SafeDateFormat {
    static final ThreadLocal<DateFormat> T_1 = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    static DateFormat get() {
        return T_1.get();
    }

    public static void remove() {
        T_1.remove();
    }
}
