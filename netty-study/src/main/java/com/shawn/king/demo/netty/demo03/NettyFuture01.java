package com.shawn.king.demo.netty.demo03;

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @author Shawn
 * @date 2023/8/6 13:03
 * @description
 */
@Slf4j
public class NettyFuture01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();
        log.debug("before submit...");
        Future<Integer> future = eventLoop.submit(() -> {
            log.debug("executing...");
            Thread.sleep(1000);
            return 70;
        });

        // 同步
//        log.debug("waiting...");
//        log.debug("future: {}", future.get());

        // 异步
        future.addListener(future1 -> log.debug("receive result...{}", future1.getNow()));
    }
}
