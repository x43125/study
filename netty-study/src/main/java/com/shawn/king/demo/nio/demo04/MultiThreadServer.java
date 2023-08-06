package com.shawn.king.demo.nio.demo04;

import com.shawn.king.utils.BufferUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Shawn
 * @date 2023/8/5 21:32
 * @description
 */
@Slf4j
public class MultiThreadServer {
    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boss");
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8080));

        // boss selector
        Selector boss = Selector.open();
        // 把boss selector注册到ServerSocketChannel之后，会得到boss的SelectionKey
        SelectionKey bossKey = ssc.register(boss, 0, null);
        // 让bossKey只关心 accept
        bossKey.interestOps(SelectionKey.OP_ACCEPT);

        // 1. 创建固定数量的worker并初始化
        int cpus = Runtime.getRuntime().availableProcessors();
        System.out.println("cpus = " + cpus);
        Worker[] workers = new Worker[cpus];
        for (int i = 0; i < cpus; i++) {
            workers[i] = new Worker("worker-" + i);
        }

        AtomicInteger count = new AtomicInteger(0);

        while (true) {
            boss.select();
            Iterator<SelectionKey> iter = boss.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                iter.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    log.debug("connected...{}", sc.getRemoteAddress());
                    // 2.关联 selector
                    log.debug("before register...{}", sc.getRemoteAddress());
                    // 如果count是Integer.MAX_VALUE则恢复成0
                    count.compareAndSet(workers.length, 0);
                    System.out.println(count);
                    workers[count.getAndIncrement() % workers.length].register(sc);
//                    worker.register(sc);
//                    sc.register(worker.selector, SelectionKey.OP_READ, null);
                    log.debug("after register...{}", sc.getRemoteAddress());
                }
            }
        }
    }

    static class Worker implements Runnable {
        private Thread thread;
        private Selector selector;
        private String name;
        private volatile boolean start = false;

        private ConcurrentLinkedDeque<Runnable> queue = new ConcurrentLinkedDeque<>();

        public Worker(String name) {
            this.name = name;
        }

        public void register(SocketChannel sc) throws IOException {
            if (!start) {
                // 把自己传给自己
                thread = new Thread(this);
                thread.start();
                selector = Selector.open();
                start = true;
            }

            // 将注册任务放到队列里
            queue.add(() -> {
                try {
                    sc.register(selector, SelectionKey.OP_READ, null);
                } catch (ClosedChannelException e) {
                    throw new RuntimeException(e);
                }
            });

            // 唤醒selector.select
            selector.wakeup();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();

                    // 取出来，如果不为空，则执行 run方法。一种控制线程间消息传递的方式，
                    Runnable task = queue.poll();
                    if (task != null) {
                        task.run();
                    }

                    Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();
                        if (key.isReadable()) {
                            try {
                                ByteBuffer buffer = ByteBuffer.allocate(16);
                                SocketChannel channel = (SocketChannel) key.channel();
                                log.debug("read...{}", channel.getRemoteAddress());
                                // 如果是断开连接，就关闭
                                int read = channel.read(buffer);
                                if (read == -1) {
                                    key.cancel();
                                } else {
                                    buffer.flip();
                                    BufferUtils.debugRead(buffer);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                key.cancel();
                            }
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
