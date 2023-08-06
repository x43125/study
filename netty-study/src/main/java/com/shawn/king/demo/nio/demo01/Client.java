package com.shawn.king.demo.nio.demo01;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;

/**
 * @author Shawn
 * @date 2023/8/5 00:13
 * @description
 */
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8080));
        SocketAddress localAddress = sc.getLocalAddress();
        System.out.println("waiting...");
    }
}
