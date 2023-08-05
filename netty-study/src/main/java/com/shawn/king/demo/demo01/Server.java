package com.shawn.king.demo.demo01;

import com.shawn.king.utils.BufferUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shawn
 * @date 2023/8/5 00:13
 * @description
 */
@Slf4j
public class Server {
    public static void main(String[] args) {
        try {
            // 0. 申请用于存储传入内容的buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 1. 创建服务器
            ServerSocketChannel ssc = ServerSocketChannel.open();
            // 非阻塞模式
            ssc.configureBlocking(false);
            // 2. 绑定端口
            ssc.bind(new InetSocketAddress(8080));
            // 3. 链接集合
            List<SocketChannel> channels = new ArrayList<>();
            while (true) {
                // 4. accept建立与客户端连接 SocketChannel用来与客户端通信
                SocketChannel sc = ssc.accept();
                if (sc != null) {
                    log.debug("connected... {}", sc);
                    sc.configureBlocking(false);
                    channels.add(sc);
                }

                for (SocketChannel channel : channels) {
                    int read = channel.read(buffer);
                    if (read > 0) {
                        buffer.flip();
                        BufferUtils.debugRead(buffer);
                        buffer.clear();
                        log.debug("after read... {}", channel);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
