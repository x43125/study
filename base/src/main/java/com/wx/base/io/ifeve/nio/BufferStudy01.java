package com.wx.base.io.ifeve.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/04/23
 */
public class BufferStudy01 {

    public static void main(String[] args) {
        String filePath = "F:\\study\\io-study\\src\\main\\resources\\test1.txt";

        try (FileInputStream is = new FileInputStream(filePath);
             FileChannel inChannel = is.getChannel();
        ) {
            ByteBuffer buffer = ByteBuffer.allocate(100);
            int read;
            while ((read = inChannel.read(buffer)) != -1) {
                System.out.println("read: " + read);
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                System.out.println();
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
