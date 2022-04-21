package com.wx.bio;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Descrption: channel
 * @Author: x43125
 * @Date: 22/04/21
 */
public class ChannelStudy05 {

    public static void main(String[] args) {
        String filePath = "F:\\test\\test.txt";

        try (
             RandomAccessFile rw = new RandomAccessFile(filePath, "rw");
             FileChannel channel = rw.getChannel();
        ) {
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
            map.put(0, (byte)'H');
            map.put(3, (byte)'9');
            System.out.println("modify successfully");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
