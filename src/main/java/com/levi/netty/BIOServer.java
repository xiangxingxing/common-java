package com.levi.netty;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) {
        connect();
    }

    //线程池机制
    //1.创建线程池
    //2.若有客户端连接，则创建一个线程与之通讯
    public static void connect(){
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        //创建一个serverSocket
        try {
            ServerSocket serverSocket = new ServerSocket(5566);
            System.out.println("服务器启动了");
            while (true){
                //监听，等待客户端连接
                final Socket socket = serverSocket.accept();
                System.out.println("连接到一个客户端");

                //创建一个线程，与之通讯
                newCachedThreadPool.execute(() -> {
                    try {
                        handler(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handler(Socket socket) throws IOException {

        try(var inputStream = socket.getInputStream();
            var in = new Scanner(inputStream, StandardCharsets.UTF_8)){
/*
            byte[] bytes = new byte[1024];
            //通过socket获取输入流
            int readCount = 0;
            while ((readCount = inputStream.read(bytes)) != -1){
                System.out.println(new String(bytes, 0 ,readCount));
            }
            */
            while (in.hasNextLine()){
                String line = in.nextLine();
                System.out.println(line);
            }
        }

    }
}
