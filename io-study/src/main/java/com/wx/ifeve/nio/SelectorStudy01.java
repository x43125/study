package com.wx.ifeve.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
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
                    ByteBuffer buffer = ByteBuffer.allocate(100);
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();

                        if (key.isAcceptable()) {
                            System.out.println("key.isAcceptable");
                            // 说明当前 channel是一个连接 channel，故应该将其注册到selector中
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            if (socketChannel == null) continue;
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println("客户端 " + socketChannel.getRemoteAddress() + " 连接成功");
                        } else if (key.isReadable()) {
                            System.out.println("key.isReadable");
                            SocketChannel socketChannel = null;

                            try {
                                socketChannel = (SocketChannel) key.channel();
                                socketChannel.configureBlocking(false);
                                socketChannel.read(buffer);
                                String msg = new String(buffer.array());
                                System.out.println(msg.trim());
                                buffer.clear();
                            } catch (IOException e) {
                                System.out.println("客户端下线");
                                socketChannel.close();
                            }
                        } else if (key.isConnectable()) {
                            System.out.println("key.isConnectable");
                        } else if (key.isWritable()) {
                            System.out.println("key.isWritable");
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
