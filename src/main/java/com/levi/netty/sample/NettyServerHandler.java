package com.levi.netty.sample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取实际数据（客户端）
     * 1.ChannelHandlerContext ctx：上下文对象 含有管道pipeline、通道channel，客户端地址
     * 2.msg：客户端发送的数据
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx = " + ctx);

        ByteBuf buf = (ByteBuf)msg;
        System.out.println("message from client: " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址为：" + ctx.channel().remoteAddress());

    }

    /**
     * 数据读取完毕
     * */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入缓冲并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello！客户端",CharsetUtil.UTF_8));
    }

    /**
     * 异常处理:一般需要关闭通道
     * */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
