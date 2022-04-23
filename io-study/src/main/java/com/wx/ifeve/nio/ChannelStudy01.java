package com.wx.ifeve.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/04/23
 */
public class ChannelStudy01 {
    public static void main(String[] args) {
        String readFilePath = "F:\\study\\io-study\\src\\main\\resources\\test1.txt";
        String writeFilePath = "F:\\study\\io-study\\src\\main\\resources\\test2.txt";

        try (FileInputStream fis = new FileInputStream(readFilePath);
             FileChannel readChannel = fis.getChannel();
             FileOutputStream fos = new FileOutputStream(writeFilePath);
             FileChannel writeChannel = fos.getChannel();
        ) {
            ByteBuffer buffer = ByteBuffer.allocate(10);
            int read = readChannel.read(buffer);
            while (read != -1) {
                buffer.flip();
                System.out.println("Read " + read);
                writeChannel.write(buffer);
                buffer.clear();
                read = readChannel.read(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
