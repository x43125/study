package com.wx.ifeve.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/04/23
 */
public class SelectorStudy01 {
    public static void main(String[] args) {

        try (Selector selector = Selector.open();
             ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ) {
            System.out.println("服务器启动成功：");
            serverSocketChannel.socket().bind(new InetSocketAddress(6666));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                int channels = selector.select();
                if (channels > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            // 说明当前 channel是一个连接 channel，故应该将其注册到selector中
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println("客户端 " + socketChannel.getRemoteAddress() + " 连接成功");
                        } else if (key.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            socketChannel.configureBlocking(false);
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            socketChannel.read(buffer);
                            System.out.println(new String(buffer.array()));
                        } else if (key.isConnectable()) {

                        } else if (key.isWritable()) {

                        }
                        iterator.remove();
                    }
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
