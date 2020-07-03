package com.levi.netty.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class GroupChatServer {
    //属性
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;


    public GroupChatServer(){
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

    public void listen(){
        try {
            while (true){
                var count = selector.select(1000);
                if (count > 0){
                    //有事件要处理
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        //取出selectionKey
                        SelectionKey key = iterator.next();
                        //处理连接
                        if (key.isAcceptable()){
                            SocketChannel channel = listenChannel.accept();
                            channel.configureBlocking(false);

                            channel.register(selector, SelectionKey.OP_READ);
                            //提示
                            System.out.println(channel.getRemoteAddress() + "上线 ");
                        }
                        //通道发送read事件
                        if (key.isReadable()){

                        }

                        iterator.remove();
                    }
                }
                else {
                    System.out.println("waiting...");
                }
            }

        }catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
