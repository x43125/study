package com.wx.nio;

import java.nio.IntBuffer;

/**
 * @Descrption: Channel Buffer Selector
 *   buffer
 * @Author: x43125
 * @Date: 22/04/21
 */
public class NioStudy01 {
    public static void main(String[] args) {
        // create a buffer with 5 capacity
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }
//        intBuffer.put(1111);

        // exchange read and write to get intBuffer's data
        intBuffer.flip();
        // set new position, read action will begin at here
//        intBuffer.position(1);
        // set new limit, read will end at here
//        intBuffer.limit(3);

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
