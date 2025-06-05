package com.wx.base.io.ab.chatroom.version3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author Shawn
 * @date 2025/5/30 23:10
 * @description
 */
public class ChatRoomUser {
    private static final String HOST = "localhost";
    private static final int PORT = 8888;
    private SocketChannel socketChannel;
    private String nickname;

    public ChatRoomUser() {
        try {
            // 1. 连接服务器
            socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
            socketChannel.configureBlocking(false);

            // 2. 启动接收线程
            new Thread(this::receiveMsg).start();

            // 3. 启动发送消息
            sendMsg();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveMsg() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            while (true) {
                buffer.clear();
                int readBytes = socketChannel.read(buffer);

                if (readBytes > 0) {
                    buffer.flip();
                    String msg = new String(buffer.array(), 0, readBytes);
                    System.out.println(msg);
                }

                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMsg() {
        Scanner scanner = new Scanner(System.in);
        try {
            // 1. 首先输入昵称
            System.out.print("请输入昵称: ");
            nickname = scanner.nextLine();
            socketChannel.write(ByteBuffer.wrap(nickname.getBytes()));

            // 2. 然后开始聊天
            while (true) {
                String msg = scanner.nextLine();
                socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    public static void main(String[] args) {
        new ChatRoomUser();
    }

}
