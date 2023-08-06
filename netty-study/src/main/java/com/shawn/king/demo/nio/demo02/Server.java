package com.shawn.king.demo.nio.demo02;

import com.shawn.king.utils.BufferUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Shawn
 * @date 2023/8/5 00:13
 * @description
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        // 创建Selector 管理多个channel
        Selector selector = Selector.open();
        // 1. 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 非阻塞模式
        ssc.configureBlocking(false);
        // 将channel注册到selector上
        // SelectionKey 就是将来事件发生后，通过它可以知道事件和哪个channel的事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // 设置key只关注accept事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.debug("register key: {}", sscKey);
        // 2. 绑定端口
        ssc.bind(new InetSocketAddress(8080));
        while (true) {
            // 3. select方法：没有事件->线程阻塞，有事件->线程才会恢复运行
            selector.select();
            // 4. 处理事件, selectedKeys 内部包含了所有发生的事件
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                // 处理key后要删除key，否则将会在下次处理的时候出问题
                iter.remove();
                log.debug("key: {}", key);
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    // 接收到一个客户端请求
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);

                    ByteBuffer buffer = ByteBuffer.allocate(16);
                    // 将该channel注册到selector上
                    SelectionKey scKey = sc.register(selector, 0, buffer);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.debug("{}", sc);
                } else if (key.isReadable()) {
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        int read = channel.read(buffer);
                        if (read == -1) {
                            key.cancel();
                        } else {
                            BufferUtils.split(buffer);
                            if (buffer.position() == buffer.limit()) {
                                ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                                buffer.flip();
                                newBuffer.put(buffer);
                                key.attach(newBuffer);
                            } else {
                                BufferUtils.debugRead(buffer);
                                buffer.clear();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel();
                    }
                }
            }
        }
    }


}
