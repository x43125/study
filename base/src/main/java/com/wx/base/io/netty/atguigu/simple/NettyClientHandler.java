//package com.wx.base.io.netty.atguigu.simple;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//
//import java.nio.charset.StandardCharsets;
//
///**
// * @Descrption:
// * @Author: x43125
// * @Date: 22/06/04
// */
//public class NettyClientHandler extends ChannelInboundHandlerAdapter {
//    /**
//     * 当通道就绪就会触发
//     *
//     * @param ctx
//     * @throws Exception
//     */
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("client " + ctx);
//        ctx.writeAndFlush(Unpooled.copiedBuffer("hello server: ^^", StandardCharsets.UTF_8));
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        System.out.println("server's msg: " + buf.toString(StandardCharsets.UTF_8));
//        System.out.println("server host: " + ctx.channel().remoteAddress());
//    }
//}
