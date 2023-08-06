package com.shawn.king.demo.netty.demo05;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author Shawn
 * @date 2023/8/6 16:48
 * @description
 */
public class RedisClientSimulator {
    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        final byte[] LINE = {13, 10};

        try {
            Bootstrap bootstrap = new Bootstrap()
                    .channel(NioSocketChannel.class)
                    .group(worker)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new LoggingHandler())
                                    .addLast(new ChannelInboundHandlerAdapter() {
                                        // 连接建立的时候
                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            ByteBuf buffer = ctx.alloc().buffer();
                                            buffer.writeBytes("*3".getBytes()).writeBytes(LINE);
                                            buffer.writeBytes("$3".getBytes()).writeBytes(LINE);
                                            buffer.writeBytes("set".getBytes()).writeBytes(LINE);
                                            buffer.writeBytes("$4".getBytes()).writeBytes(LINE);
                                            buffer.writeBytes("name".getBytes()).writeBytes(LINE);
                                            buffer.writeBytes("$8".getBytes()).writeBytes(LINE);
                                            buffer.writeBytes("zhangsan".getBytes()).writeBytes(LINE);
                                            ctx.writeAndFlush(buffer);
                                        }

                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                            ByteBuf buffer = (ByteBuf) msg;
                                            System.out.println(buffer.toString(Charset.defaultCharset()));
                                        }
                                    });
                        }
                    });

            ChannelFuture future = bootstrap.connect("localhost", 6379).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
