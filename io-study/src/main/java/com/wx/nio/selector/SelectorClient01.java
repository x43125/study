package com.wx.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @Description:
 * @Author: wx
 * @Date: 2022/04/22 14:50
 */
public class SelectorClient01 {
    public static void main(String[] args) {
        try (
                SocketChannel socketChannel = SocketChannel.open();
        ) {
            socketChannel.configureBlocking(false);
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
            if (!socketChannel.connect(inetSocketAddress)) {
                while (!socketChannel.finishConnect()) {
                    System.out.println("连接需要时间，客户端不会阻塞，可以做别的事情");
                }
            }
            String str = "hello world";
            ByteBuffer buffer = ByteBuffer.wrap(str.getBytes(StandardCharsets.UTF_8));
            socketChannel.write(buffer);
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
