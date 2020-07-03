package com.levi.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();

        //得到一个selector选择器对象
        Selector selector = Selector.open();

        //绑定端口6666，服务器监听
        ssc.socket().bind(new InetSocketAddress(6666));

        //设置为非阻塞，因为要注册到selector，Channel必须为非阻塞模式
        ssc.configureBlocking(false);

        //将服务器的serverSocketChannel注册到selector上，关心事件为OP_ACCEPT
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while (true) {
            //等待一秒，如果没有事件发生则返回
            if (selector.select(1000) == 0) {
                System.out.println("服务器等了1秒，无连接");
                continue;
            }

            /*
                ‼️获取相关的selectionKeys集合‼️
                1.select() > 0 表示已经获取到关注的事件
                2.selectionKeys为关注事件的集合
                通过selectionKeys可以反向获取通道
            */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //如果有新的客户端连接,则为该客户端生成一个socketChannel
                if (key.isAcceptable()) {
                    SocketChannel socketChannel = ssc.accept();
                    //‼️需将新连接的客户端channel设置为非阻塞的‼️
                    socketChannel.configureBlocking(false);
                    System.out.println("客户端连接成功，生成了一个socketChannel：" + socketChannel.hashCode());
                    //客户端的socketChannel也需要注册到selector上,同时关联了一个Buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(20));

                }
                //发生OP_READ事件，表明客户端
                if (key.isReadable()) {
                    //通过key反向获取该客户端的对应的channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取到该channel关联的buffer
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();//得到与key关联的共享数据
                    //将当前通道数据读入到buffer
                    channel.read(byteBuffer);
                    System.out.println("from client " + new String(byteBuffer.array()).trim());

                }

                //手动从集合中移除当前的selectionKey，防止重复操作
                iterator.remove();
            }

        }


    }
}
