package com.wx.netty.atguigu.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/06/04
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取到客户端的消息
     *
     * @param ctx 上下文信息
     * @param msg 客户端发来的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 使用的是一个单线程的线程池
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer("1 hello, client~", StandardCharsets.UTF_8));
        });
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer("2 hello, client~", StandardCharsets.UTF_8));
        });

//        System.out.println("server read thread: " + Thread.currentThread().getName());
//        System.out.println("server ctx: " + ctx);
////        将msg转成一个 ByteBuf (netty提供的)
//        ByteBuf buf = (ByteBuf) msg;
//        System.out.println("client host: " + ctx.channel().remoteAddress());
//        System.out.println("client send msg: " + buf.toString(StandardCharsets.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // write + flush 将数据写入缓冲并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, client~", StandardCharsets.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
