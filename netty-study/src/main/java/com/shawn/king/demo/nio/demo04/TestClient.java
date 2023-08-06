package com.shawn.king.demo.nio.demo04;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author Shawn
 * @date 2023/8/5 21:48
 * @description
 */
public class TestClient {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8080));
        sc.write(Charset.defaultCharset().encode("1234567890abcdef"));
        System.in.read();
    }
}
