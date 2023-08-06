package com.shawn.king.demo.netty.demo02;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author Shawn
 * @date 2023/8/6 10:46
 * @description
 */
@Slf4j
public class ServerBootStrap02 {
    public static void main(String[] args) {
        // 服务端的代码，几乎都是固定的模板，除了你的handler；也就是：0-4基本都是一样的，除了5之后
        EventLoopGroup group = new DefaultEventLoopGroup();
        // 0
        new ServerBootstrap()
                // 1 第一个用于accept 第二个用于 read/write
                .group(new NioEventLoopGroup(), new NioEventLoopGroup(2))
                // 2
                .channel(NioServerSocketChannel.class)
                // 3
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    // 4
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 5
                        ch.pipeline()
                                .addLast("handler-01", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ByteBuf buf = (ByteBuf) msg;
                                        log.debug(buf.toString(Charset.defaultCharset()));
                                        // 将此消息传递给下一个handler执行
                                        ctx.fireChannelRead(msg);
                                    }
                                })
                                // 下一个handler
                                .addLast(group, "handler-02", new ChannelInboundHandlerAdapter() {
                                    // 这里会由group中的线程来处理此任务
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ByteBuf buf = (ByteBuf) msg;
                                        log.debug(buf.toString(Charset.defaultCharset()));
                                    }
                                });
                    }
                })
                .bind(8080);
    }
}
