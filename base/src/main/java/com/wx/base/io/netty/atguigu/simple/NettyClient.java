package com.wx.base.io.netty.atguigu.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/06/04
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
//        客户端只需要一个事件循环组
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyClientHandler());
                        }
                    });

            System.out.println("client is ok");
            ChannelFuture cf = bootstrap.connect("127.0.0.1", 6666).sync();
            cf.channel().closeFuture().sync();
        } finally {
            eventExecutors.shutdownGracefully();
        }

    }
}
