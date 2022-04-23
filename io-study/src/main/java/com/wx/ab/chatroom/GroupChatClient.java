package com.wx.ab.chatroom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/04/23
 */
public class GroupChatClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 6667;

    private final Selector selector;
    private final SocketChannel socketChannel;

    private final String username;

    public GroupChatClient() {
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
            this.socketChannel.configureBlocking(false);
            this.socketChannel.register(selector, SelectionKey.OP_READ);
            this.username = this.socketChannel.getLocalAddress().toString().substring(1);
            System.out.println(this.username + " is ok...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void send(String msg) {
        msg = username + " 说: " + msg;
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() {
        try {
            int channels = selector.select();
            if (channels > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        sc.read(buffer);
                        String msg = new String(buffer.array());
                        buffer.clear();
                        System.out.println("接收到消息：" + msg.trim());
                        System.out.println("消息长度: " + msg.length());
                    }
                }
            } else {
                System.out.println("没有可用的");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        GroupChatClient client = new GroupChatClient();
        new Thread(() -> {
            while (true) {
                try {
                    client.read();
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            client.send(line);
        }
    }
}
