package com.levi.netty.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {
    //属性
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    public static void main(String[] args) {
        GroupChatServer server = new GroupChatServer();
        server.listen();
    }


    public GroupChatServer() {
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));

            //设置非阻塞模式
            listenChannel.configureBlocking(false);
            //注册
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
                var count = selector.select();//阻塞
                if (count > 0) {
                    //有事件要处理
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        //取出selectionKey
                        SelectionKey key = iterator.next();
                        //处理连接
                        if (key.isAcceptable()) {
                            SocketChannel channel = listenChannel.accept();
                            channel.configureBlocking(false);

                            channel.register(selector, SelectionKey.OP_READ);
                            //提示
                            System.out.println(channel.getRemoteAddress() + "上线了 ");
                        }
                        //通道发送read事件
                        if (key.isReadable()) {
                            readData(key);
                        }

                        iterator.remove();
                    }
                } else {
                    System.out.println("waiting...");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    //读取客户端消息
    private void readData(SelectionKey key) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int read = channel.read(buffer);
            if (read > 0) {
                String msg = new String(buffer.array());
                System.out.println("from client" + msg.trim());

                //向其他客户端转发消息[排除自身]
                forwardInfo(msg, channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了..");
                //取消注册
                key.cancel();
                //关闭通道
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //向客户（通道）转发消息
    private void forwardInfo(String msg, SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中...");
        for (SelectionKey key : selector.keys()) {
            //取出对应的socketChannel
            Channel targetChannel = key.channel();

            //排除自己
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                SocketChannel dest = (SocketChannel) targetChannel;
                //将msg转成buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes("utf-8"));
                //写入通道
                dest.write(buffer);

            }
        }


    }
}
