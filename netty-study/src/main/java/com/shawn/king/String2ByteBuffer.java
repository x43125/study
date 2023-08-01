package com.shawn.king;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author Shawn
 * @date 2023/8/1 00:07
 * @description
 */
public class String2ByteBuffer {
    public static void main(String[] args) {
        // string 2 buffer
        // func1
        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        buffer1.put("abc".getBytes());

        // func2
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("abc");

        // func3
        ByteBuffer buffer3 = ByteBuffer.wrap("abc".getBytes());

        // buffer 2 string
        // func3
        String str2 = StandardCharsets.UTF_8.decode(buffer2).toString();
        System.out.println(str2);

        buffer1.flip();
        String str1 = StandardCharsets.UTF_8.decode(buffer1).toString();
        System.out.println(str1);
    }
}
