package com.wx.base.io.ab.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @Descrption: channel
 * @Author: x43125
 * @Date: 22/04/21
 */
public class ChannelStudy04 {

    public static void main(String[] args) {
        String filePath = "F:\\test\\ppdream-logo.jpg";
        String filePath2 = "F:\\test\\ppdream-logo-2.jpg";

        File file = new File(filePath);

        try (FileInputStream fis = new FileInputStream(file);
             FileChannel fisChannel = fis.getChannel();
             FileOutputStream fos = new FileOutputStream(filePath2);
             FileChannel fosChannel = fos.getChannel()
        ) {
            fosChannel.transferFrom(fisChannel, 0, fisChannel.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
