package com.wx.base.io.ifeve.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/04/23
 */
public class SelectorStudy02 {
    public static void main(String[] args) {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();) {
            // 绑端口号
            serverSocketChannel.bind(new InetSocketAddress(6666));
            // 设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            // 注册监听链接事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            // 循环监听
            while (true) {
                // 阻塞获取监听事件
                selector.select();
                // 说明有事件了
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 循环所有事件
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    //
                    iterator.remove();
                    if (key.isAcceptable()) {
                        // 处理链接channel
                        System.out.println("key.isAcceptable");
                        // 说明当前 channel是一个连接 channel，故应该将其注册到selector中
                        SocketChannel clientChannel = serverSocketChannel.accept();
                        if (clientChannel == null) continue;
                        clientChannel.configureBlocking(false);
                        // 为请求链接的客户端注册到selector，并为其开放读取权限
                        clientChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("客户端 " + clientChannel.getRemoteAddress() + " 连接成功");
                    } else if (key.isReadable()) {
                        // 处理读取数据
                        System.out.println("key.isReadable");
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.configureBlocking(false);
                        socketChannel.read(buffer);
                        String msg = new String(buffer.array());
                        System.out.println(msg.trim());
                        buffer.clear();
                    } else if (key.isWritable()) {
                        System.out.println("key.isWritable");
                        String returnWord = "hi, i accepted your message";
                        byte[] bytes = returnWord.getBytes(StandardCharsets.UTF_8);
                        ByteBuffer writeBuffer = ByteBuffer.wrap(bytes);
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.write(writeBuffer);
                    } else if (key.isConnectable()) {
                        System.out.println("key.isConnectable");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
