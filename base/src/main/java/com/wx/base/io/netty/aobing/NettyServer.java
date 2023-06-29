//package com.wx.base.io.netty.aobing;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelPipeline;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.codec.string.StringDecoder;
//import io.netty.handler.codec.string.StringEncoder;
//
///**
// * @Description:
// * @Author: wx
// * @Date: 2022/04/25 15:49
// */
//public class NettyServer {
//    public static void main(String[] args) throws InterruptedException {
//        EventLoopGroup parentGroup = new NioEventLoopGroup();
//        EventLoopGroup childGroup = new NioEventLoopGroup();
//        try {
//            ServerBootstrap bootstrap = new ServerBootstrap();
//            bootstrap.group(parentGroup, childGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        protected void initChannel(SocketChannel ch) {
//                            ChannelPipeline pipeline = ch.pipeline();
//                            pipeline.addLast(new StringDecoder());
//                            pipeline.addLast(new StringEncoder());
//                            pipeline.addLast(new DemoSocketServerHandler());
//                        }
//                    });
//
//            ChannelFuture future = bootstrap.bind(8888).sync();
//            System.out.println("服务器已启动。。。");
//
//            future.channel().closeFuture().sync();
//        } finally {
//            parentGroup.shutdownGracefully();
//            childGroup.shutdownGracefully();
//        }
//    }
//}
