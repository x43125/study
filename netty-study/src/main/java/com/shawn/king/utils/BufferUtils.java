package com.shawn.king.utils;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * @author Shawn
 * @date 2023/8/5 00:45
 * @description
 */
@Slf4j
public class BufferUtils {
    public static void debugRead(ByteBuffer buffer) {
        while (buffer.hasRemaining()) {
            char c = (char) buffer.get();
            log.info("读取到的字节： {}", c);
        }
    }

    public static void split(ByteBuffer buffer) {
        buffer.flip();
        for (int i = 0; i < buffer.limit(); i++) {
            if (buffer.get(i) == '\n') {
                int length = i + 1 - buffer.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int j = 0; j < length; j++) {
                    target.put(buffer.get());
                }
                debugRead(target);
            }
        }
        buffer.compact();
    }
}
