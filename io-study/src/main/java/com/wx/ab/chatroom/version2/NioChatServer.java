package com.wx.ab.chatroom.version2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author x43125
 */
public class NioChatServer {
    public static void main(String[] args) {
        NioChatServer server = new NioChatServer();
        System.out.println("服务已启动: " + server.serverName);
        server.run();
    }

    private static final int PORT = 6666;
    private static final String HOST = "127.0.0.1";

    private String serverName;
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public NioChatServer() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(HOST, PORT));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            serverName = serverSocketChannel.getLocalAddress().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            boolean stop = false;
            while (!stop) {
                int select = selector.select();
                if (select <= 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();

                    if (key.isAcceptable()) {
                        register();
                    } else if (key.isReadable()) {
                        read(key);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocketChannel.close();
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void register() {
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel == null) {
                return;
            }

            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("客户端：" + socketChannel.getRemoteAddress() + " 连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key) {
        SelectableChannel channel = key.channel();
        if (channel instanceof SocketChannel) {
            SocketChannel socketChannel = (SocketChannel) channel;
            try {
                ByteBuffer buffer = ByteBuffer.allocate(128);
                int read = socketChannel.read(buffer);

                if (read == -1) {
                    System.out.println(socketChannel.getRemoteAddress() + " 已离线");
                    key.cancel();
                    channel.close();
                    return;
                }
                String msg = new String(buffer.array(), StandardCharsets.UTF_8);
                String trim = msg.trim();
                if (trim.length() == 0) {
                    return;
                }

                System.out.println(socketChannel.getRemoteAddress() + " 发来消息: " + trim);
                forwardMsg(msg, socketChannel);
                buffer.clear();
            } catch (IOException e) {
                System.out.println("客户端已断开连接");
                key.cancel();
                try {
                    channel.close();
                } catch (IOException o) {
                    o.printStackTrace();
                }
            }
        }
    }

    private void forwardMsg(String msg, SocketChannel self) {
        String pureMsg = msg.trim();
        if (pureMsg.length() == 0) {
            return;
        }

        try {
            Set<SelectionKey> keys = selector.keys();
            for (SelectionKey key : keys) {
                SelectableChannel channel = key.channel();
                if (channel instanceof SocketChannel && channel != self) {
                    SocketChannel destChannel = (SocketChannel) channel;
                    System.out.println("转发消息 [" + pureMsg + "] 到：" + destChannel.getRemoteAddress());
                    destChannel.write(ByteBuffer.wrap(pureMsg.getBytes(StandardCharsets.UTF_8)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
