package com.shawn.king.demo.nio.demo03;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * @author Shawn
 * @date 2023/8/5 09:43
 * @description
 */
public class WriteServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // blocking false
        ssc.configureBlocking(false);
        Selector selector = Selector.open();
        // 注册selector
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        // 绑定端口
        ssc.bind(new InetSocketAddress(8080));

        while (true) {
            System.out.println("waiting for connection...");
            selector.select();
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                iter.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 300000; i++) {
                        sb.append("aaaaaaaaaa");
                    }
                    ByteBuffer buffer = Charset.defaultCharset().encode(sb.toString());
                    while (buffer.hasRemaining()) {
                        int write = sc.write(buffer);
                        System.out.println("write = " + write);
                    }
                }
            }
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
