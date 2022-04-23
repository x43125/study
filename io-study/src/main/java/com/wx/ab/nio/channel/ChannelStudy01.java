package com.wx.ab.nio.channel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Descrption: channel
 * @Author: x43125
 * @Date: 22/04/21
 */
public class ChannelStudy01 {
    public static void main(String[] args) {
        // channel is an interface
        // its most popular implement class are FileChannel ServerSocketChannel DatagramChannel
        // transferFrom/To read/write data indirect to direct memory (zero copy)

        String str = "hello world";
        try (FileOutputStream fos = new FileOutputStream("F:\\test\\test.txt");
             FileChannel fosChannel = fos.getChannel();) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(str.getBytes());
            byteBuffer.flip();
            fosChannel.write(byteBuffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
