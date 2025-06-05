package com.wx.base.io.ab.chatroom.version3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Shawn
 * @date 2025/5/30 22:09
 * @description
 */
public class ChatRoomServer {
    private static final Integer PORT = 8888;
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public ChatRoomServer() throws IOException {
        // 创建selector
        selector = Selector.open();

        // 创建serverSocketChannel
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(PORT));

        // 注册接受事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void start() {
        try {
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isAcceptable()) {
                        handleAccept(key);
                    }

                    if (key.isReadable()) {
                        handleRead(key);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRead(SelectionKey key) {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            int read = clientChannel.read(buffer);
            if (read > 0) {
                buffer.flip();
                String msg = StandardCharsets.UTF_8.decode(buffer).toString().trim();
                if (key.attachment() == null) {
                    key.attach(msg);
                    String welcome = "你好 " + msg + "！可以开始聊天了。";
                    clientChannel.write(ByteBuffer.wrap(welcome.getBytes(StandardCharsets.UTF_8)));
                } else {
                    String nickname = (String) key.attachment();
                    broadcast(nickname + ": " + msg);
                }
            } else if (read < 0) {
                String nickname = key.attachment() != null ? (String) key.attachment() : "匿名用户";
                System.out.println(nickname + " 离开了聊天室");
                clientChannel.close();
            }
        } catch (IOException e) {
            key.cancel();
            if (clientChannel != null) {
                try {
                    clientChannel.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    }

    private static final String welcomeWord = "欢迎加入聊天室！请输入你的昵称：";
    private void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = socketChannel.accept();
        clientChannel.configureBlocking(false);

        clientChannel.register(selector, SelectionKey.OP_READ);
        clientChannel.write(ByteBuffer.wrap(welcomeWord.getBytes(StandardCharsets.UTF_8)));
        System.out.println("客户端链接：" + clientChannel.getRemoteAddress());
    }


    private void broadcast(String msg) throws IOException {
        System.out.println("广播消息: " + msg);

        // 1. 遍历所有已注册的客户端
        for (SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();

            // 2. 只发送给SocketChannel且不是服务端Channel
            if (targetChannel instanceof SocketChannel && targetChannel != serverSocketChannel) {
                SocketChannel dest = (SocketChannel) targetChannel;
                dest.write(ByteBuffer.wrap((msg + "\n").getBytes(StandardCharsets.UTF_8)));
            }
        }
    }

    public static void main(String[] args) {
        try {
            new ChatRoomServer().start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
