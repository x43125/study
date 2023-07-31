package com.wx.base.jvm.man;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shawn
 * @date 2023/7/29 00:37
 * @description
 */
public class Demo1_9 {
    private static final String FROM = "/Users/wangxiang/Documents/books/copy.pdf";
    private static final String TO = "/Users/wangxiang/Downloads/copy.pdf";
    private static final int _1Mb = 1024 * 1024;

    public static void main(String[] args) {
        traditionalIo();
        nio();
    }

    private static void nio() {
        long start = System.nanoTime();
        try (FileChannel from = new FileInputStream(FROM).getChannel();
             FileChannel to = new FileOutputStream(TO+"1").getChannel()
        ) {
            ByteBuffer buffer = ByteBuffer.allocate(_1Mb);
            while (true) {
                int len = from.read(buffer);
                if (len == -1) {
                    break;
                }
                buffer.flip();
                to.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            long end = System.nanoTime();
            System.out.println("io 用时：" + (end - start) / 1000_000.0);
        }
    }

    private static void traditionalIo() {
        long start = System.nanoTime();
        try (FileInputStream fis = new FileInputStream(FROM);
             FileOutputStream fos = new FileOutputStream(TO)
        ) {
            byte[] buf = new byte[_1Mb];
            while (true) {
                int len = fis.read(buf);
                if (len == -1) {
                    break;
                }
                fos.write(buf, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            long end = System.nanoTime();
            System.out.println("io 用时：" + (end - start) / 1000_000.0);
        }
    }
}
