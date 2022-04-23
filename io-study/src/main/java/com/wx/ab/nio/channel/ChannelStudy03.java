package com.wx.ab.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Descrption: channel
 * @Author: x43125
 * @Date: 22/04/21
 */
public class ChannelStudy03 {

    public static void main(String[] args) {
        String filePath = "F:\\test\\test.txt";
        String filePath2 = "F:\\test\\test2.txt";

        File file = new File(filePath);

        try (FileInputStream fis = new FileInputStream(file);
             FileChannel fisChannel = fis.getChannel();
             FileOutputStream fos = new FileOutputStream(filePath2);
             FileChannel fosChannel = fos.getChannel()
        ) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (true) {
                byteBuffer.clear();
                int read = fisChannel.read(byteBuffer);
                if (read == -1) {
                    break;
                }
                byteBuffer.flip();
                fosChannel.write(byteBuffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
