package com.wx.base.juc.advance;

import com.wx.base.juc.advance.utils.JUCUtils;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Shawn
 * @date 2023/7/12 21:23
 * @description
 */
public class LinkedBlockingQueueStudy01 {
    public static void main(String[] args) {
        // 限制队列的数量：加了3的意思：这个队列最多只能有3个节点，多的会被阻塞，直到有一个节点被取出
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(3);

        new Thread(() -> {
            try {
                queue.put("zhangsan");
                JUCUtils.sysout(queue.toString());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                queue.put("lisi");
                JUCUtils.sysout(queue.toString());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                queue.put("wanger");
                JUCUtils.sysout(queue.toString());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                queue.put("zhaosi");
                JUCUtils.sysout(queue.toString());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                String take = queue.take();
                JUCUtils.sysout("take " + take + "\t" + queue.toString());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}
