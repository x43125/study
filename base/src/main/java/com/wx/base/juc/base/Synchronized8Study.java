package com.wx.base.juc.base;


import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxiang
 * @date 2023/7/5 22:52
 * @description
 */
@Slf4j
public class Synchronized8Study {
//    static Logger logger = LoggerFactory.getLogger(Synchronized8Study.class);
    public static synchronized void a() {
        try {
            Thread.sleep(1000);
            log.debug("1");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void b() {
        log.debug("2");
    }

    public static void main(String[] args) {
        Synchronized8Study s1 = new Synchronized8Study();
        Synchronized8Study s2 = new Synchronized8Study();

        new Thread(() -> a()).start();
        new Thread(() -> s2.b()).start();
    }
}
