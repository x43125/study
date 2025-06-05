package com.wx.base.io.ifeve.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author Shawn
 * @date 2025/5/30 09:31
 * @description
 */
public class ClientSocket01 {
    public static void main(String[] args) {
        try(SocketChannel clientChannel = SocketChannel.open()) {
            clientChannel.configureBlocking(false);
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
            if (!clientChannel.connect(inetSocketAddress)) {
                while (!clientChannel.finishConnect()) {
                    System.out.println("连接需要时间，客户端不会阻塞，可以做别的事情");
                }
            }
            String str = "hello world";
            ByteBuffer buffer = ByteBuffer.wrap(str.getBytes(StandardCharsets.UTF_8));
            clientChannel.write(buffer);
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
