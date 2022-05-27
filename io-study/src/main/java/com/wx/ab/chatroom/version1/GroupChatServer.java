package com.wx.ab.chatroom.version1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/04/23
 */
public class GroupChatServer {
    private final Selector selector;
    private final ServerSocketChannel listenSocketChannel;
    private static final int PORT = 6667;

    public GroupChatServer() {
        try {
            this.selector = Selector.open();
            this.listenSocketChannel = ServerSocketChannel.open();
            this.listenSocketChannel.socket().bind(new InetSocketAddress(PORT));
            this.listenSocketChannel.configureBlocking(false);
            this.listenSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
            System.out.println("开始运行...");
            System.out.println(listenSocketChannel.getLocalAddress());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void listen() {
        System.out.println("服务开始监听...");
        try {
            // while(true) 需要放在 try catch里面，才能正常的抛出RuntimeException
            while (true) {
                int count = selector.select();
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();

                        if (key.isAcceptable()) {
                            SocketChannel sc = listenSocketChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress() + " 上线了");
                        }
                        if (key.isReadable()) {
                            read(key);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if (count > 0) {
                String msg = new String(buffer.array());
                System.out.println("from 客户端 " + msg.trim());
                sendMsg2OtherClients(msg, channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了...");
                channel.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void sendMsg2OtherClients(String msg, SocketChannel self) {
        ByteBuffer buffer = ByteBuffer.wrap(msg.trim().getBytes());
        for (SelectionKey key : selector.keys()) {
            SelectableChannel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                SocketChannel dest = (SocketChannel) targetChannel;
                try {
                    System.out.println("服务器转发消息中...消息 [" + msg.trim() + "] 已发送至: " + dest.getRemoteAddress());
                    dest.write(buffer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer server = new GroupChatServer();
        server.listen();
    }
}





























