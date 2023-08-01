package com.shawn.king;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class NettyMain {
    public static void main(String[] args) {
        try (FileChannel channel = new FileInputStream("src/main/resources/test01.txt").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                int len = channel.read(buffer);
                log.info("读取到的字节长度: {}", len);
                if (len == -1) {
                    break;
                }
                buffer.flip();
                while (buffer.hasRemaining()) {
                    char c = (char) buffer.get();
                    log.info("读取到的字节： {}", c);
                }
                buffer.clear();
            }

        } catch (IOException e) {
        }
    }
}