package com.levi.basic.net;

import java.io.IOException;
import java.net.*;
import java.nio.charset.*;
import java.util.Scanner;

public class SocketSamp {
    public static void main(String[] args) {
        try {
            //samp1();
            samp2(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void samp1() throws IOException {
        String host = "time-a.nist.gov";
        int port = 13;
        int timeout = 10000;
        try (

                var s = new Socket(host,port);
                //s.setSoTimeout(timeout);
                //解决超时问题
                /*var s = new Socket();
                s.connect(new InetSocketAddress(host, port), timeout);*/
                var in = new Scanner(s.getInputStream(),StandardCharsets.UTF_8))
        {

            while (in.hasNextLine()){
                String line = in.nextLine();
                System.out.println(line);
            }
        }

    }

    //获取主机名
    public static void samp2(String[] args) throws IOException{
        if (args.length > 0){
            String host = args[0];
            InetAddress[] addresses = InetAddress.getAllByName(host);
            for (InetAddress a : addresses){
                System.out.println(a);
            }
        }
        else {
            InetAddress localHostAddress = InetAddress.getLocalHost();
            System.out.println(localHostAddress);
        }
    }
}
