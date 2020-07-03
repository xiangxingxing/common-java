package com.levi.netty.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class GroupChatClient {
    //定义相关的属性
    private final String HOST = "127.0.0.1";
    private final int PORT = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String userName;

    public static void main(String[] args) {
        GroupChatClient client = new GroupChatClient();
        //启动一线程，每三秒从服务端读取信息
        Runnable r = () -> {
            while (true){
                client.readInfo();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r).start();

        //发送数据给服务器
        try(var in = new Scanner(System.in)){
            //System.out.print("请输入信息：");
            while (true){
                String s = in.nextLine();
                client.sendInfo(s);
            }
        }

    }

    public GroupChatClient() {
        try {
            selector = Selector.open();//需要读取数据
            socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
            //设置非阻塞
            socketChannel.configureBlocking(false);
            //注册
            socketChannel.register(selector, SelectionKey.OP_READ);
            //获取userName
            userName = socketChannel.getLocalAddress().toString().substring(1);

            System.out.println(userName + " is OK.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //向服务器发送消息
    private void sendInfo(String info) {
        info = userName + " speaks: " + info;
        try {
            //将信息写入通道
            socketChannel.write(ByteBuffer.wrap(info.getBytes("utf-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //从服务器读取信息
    public void readInfo(){
        try {
            int readChannels = selector.select();//监听，返回有事件发生的通道的个数
            if (readChannels > 0){ // 有可用的通道
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if (key.isReadable()){
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        channel.read(buffer);
                        //把读取的缓冲区数据转字符串
                        String msg = new String(buffer.array());
                        System.out.println(msg.trim());
                    }
                    //移除当前的selectionKey,防止重复操作
                    iterator.remove();
                }
            }
            //无可用的通道
            else {
                //System.out.println("无可用通道.");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
