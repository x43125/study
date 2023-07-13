package com.wx.base.juc.advance;

import com.wx.base.juc.advance.utils.JUCUtils;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Shawn
 * @date 2023/7/13 23:27
 * @description
 */
public class DelayQueueStudy {
    public static void main(String[] args) {
        DelayQueue<DelayMessage> queue = new DelayQueue<>();
        // 启动一个消费者线程
        new Thread(new DelayQueueConsumer(queue)).start();
        // 向延时队列里塞任务
        for (int i = 1; i <= 5; i++) {
            // 新建一个待消费的任务，并指定了延时时间为 i*1000L
            DelayMessage delayMessage = new DelayMessage(String.valueOf(i), 5 * 1000L);
            JUCUtils.sysout("Producer publish message: " + i);
            queue.offer(delayMessage);
        }
    }
}

/**
 * 延时队列的任务
 */
class DelayMessage implements Delayed {
    private String message;   // 延迟任务中的任务数据
    private long ttl;         // 延迟任务到期时间（过期时间）

    /**
     * 构造函数
     *
     * @param message 消息实体
     * @param ttl     延迟时间，单位毫秒
     */
    public DelayMessage(String message, long ttl) {
        setMessage(message);
        this.ttl = System.currentTimeMillis() + ttl;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        // 计算该任务距离过期还剩多少时间
        long remaining = ttl - System.currentTimeMillis();
        return unit.convert(remaining, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        // 比较、排序：对任务的延时大小进行排序，将延时时间最小的任务放到队列头部
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


class DelayQueueConsumer implements Runnable {

    private final DelayQueue<DelayMessage> delayQueue;

    /**
     * 构造函数
     *
     * @param delayQueue 延迟队列
     */
    public DelayQueueConsumer(DelayQueue<DelayMessage> delayQueue) {
        this.delayQueue = delayQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 从延迟队列的头部获取已经过期的消息
                // 如果暂时没有过期消息或者队列为空，则take()方法会被阻塞，直到有过期的消息为止
                DelayMessage delayMessage = delayQueue.take();
                JUCUtils.sysout("Consumer received message: " + delayMessage.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

