package com.wx.ab.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description:
 * @Author: wx
 * @Date: 2022/04/22 14:03
 */
public class SelectorStudy01 {
    public static void main(String[] args) {
        try (
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                Selector selector = Selector.open();
        ) {
            serverSocketChannel.socket().bind(new InetSocketAddress(6666));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                if (selector.select() == 1000) {
                    System.out.println("服务器等待1秒 ============= ");
                    continue;
                }

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    if (next.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        System.out.println("客户端连接成功，生成一个channel");
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    }

                    if (next.isReadable()) {
                        SocketChannel channel = (SocketChannel) next.channel();
                        ByteBuffer buffer = (ByteBuffer) next.attachment();
                        channel.read(buffer);
                        System.out.println("取到的数据： " + new String(buffer.array()));
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
