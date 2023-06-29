//package com.wx.base.io.netty.aobing;
//
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @Description:
// * @Author: wx
// * @Date: 2022/04/25 15:54
// */
//public class DemoSocketClientHandler extends ChannelInboundHandlerAdapter {
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println(msg);
//        ctx.channel().writeAndFlush("from client: " + System.currentTimeMillis());
//        TimeUnit.MILLISECONDS.sleep(5000);
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) {
//        ctx.channel().writeAndFlush("from client：begin talking");
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        cause.printStackTrace();
//        ctx.close();
//    }
//}
//
