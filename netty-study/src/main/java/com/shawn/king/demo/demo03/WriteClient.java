package com.shawn.king.demo.demo03;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author Shawn
 * @date 2023/8/5 09:47
 * @description
 */
public class WriteClient {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
//        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress("localhost", 8080));

        int count = 0;
        while (true) {
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
            count += sc.read(buffer);
            System.out.println("count = " + count);
            buffer.clear();
        }
    }
}
