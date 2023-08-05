package com.shawn.king.demo.demo05;

import com.shawn.king.utils.BufferUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Shawn
 * @date 2023/8/5 23:02
 * @description
 */
@Slf4j
public class AioFileChannel {
    public static void main(String[] args) throws IOException {
        try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get("src/main/resources/test01.txt"), StandardOpenOption.READ)) {
            //
            ByteBuffer buffer = ByteBuffer.allocate(16);
            log.debug("begin...");
            channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                // 读取成功
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    log.debug("read completed...");
                    attachment.flip();
                    BufferUtils.debugRead(attachment);
                    attachment.clear();
                }

                // 读取失败
                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    exc.printStackTrace();
                }
            });

            log.debug("finished...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.in.read();
    }


}
