package com.wx.base.juc.base;

import com.wx.base.juc.advance.utils.JUCUtils;

/**
 * @author Shawn
 * @date 2023/7/12 22:26
 * @description
 */
public class Interrupt2PhaseStudy03 {
    public static void main(String[] args) {
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        twoPhaseTermination.start();
        JUCUtils.sleeper(3500);
        twoPhaseTermination.stop();
    }
}


class TwoPhaseTermination {
    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            for (; ; ) {
                Thread thread = Thread.currentThread();
                if (thread.isInterrupted()) {
                    JUCUtils.sysout("料理后事");
                    break;
                }
                try {

                    Thread.sleep(1000);
                    JUCUtils.sysout("执行监控记录");
                } catch (InterruptedException e) {
                    thread.interrupt();
                    e.printStackTrace();
                }
            }
        });

        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }

}