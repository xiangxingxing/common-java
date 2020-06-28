package com.levi.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

    public static void main(String[] args) throws IOException {
        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞模式
        socketChannel.configureBlocking(false);
        //提供服务器的ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)) {
            //非阻塞循环
            while (!socketChannel.finishConnect()) {
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其他工作");
            }
        }

        //连接成功，发送数据
        String str = "hello server!";
        ByteBuffer wrap = ByteBuffer.wrap(str.getBytes());//包装字节数组，不用指定byteBuffer数组大小
        //将buffer数据写入channel
        socketChannel.write(wrap);
        System.in.read();//停顿
    }
}
