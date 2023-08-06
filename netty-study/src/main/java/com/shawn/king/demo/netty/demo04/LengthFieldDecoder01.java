package com.shawn.king.demo.netty.demo04;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author Shawn
 * @date 2023/8/6 16:31
 * @description
 */
public class LengthFieldDecoder01 {
    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 0),
                new LoggingHandler(LogLevel.DEBUG)
        );

        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();

        send(buffer, "Hello World");
        send(buffer, "Hi");
        channel.writeInbound(buffer);
    }

    private static void send(ByteBuf buffer, String msg) {
        byte[] bytes = msg.getBytes();
        int length = bytes.length;
        buffer.writeByte(length);
        buffer.writeBytes(bytes);
    }
}
