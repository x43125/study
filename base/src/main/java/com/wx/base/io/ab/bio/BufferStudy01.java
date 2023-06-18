package com.wx.base.io.ab.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/04/21
 */
public class BufferStudy01 {

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // bio使用情况，没开一个请求，后台都会启一个线程
        try (ServerSocket serverSocket = new ServerSocket(6666);) {
            System.out.println("服务器启动了...");
            while (true) {
                Socket socket = serverSocket.accept();
                cachedThreadPool.execute(() -> handler(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void handler(Socket socket) {
        try {
            byte[] bytes = new byte[1024];
            InputStream is = socket.getInputStream();
            int read;
            while ((read = is.read(bytes)) != -1) {
                String receiveMsg = new String(bytes, 0, read);
                receiveMsg = receiveMsg.trim();
                System.out.println(Thread.currentThread().getName() + " 接收到消息: " + receiveMsg);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
