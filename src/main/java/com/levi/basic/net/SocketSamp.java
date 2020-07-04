package com.levi.basic.net;

import java.io.IOException;
import java.net.*;
import java.nio.charset.*;
import java.util.Enumeration;
import java.util.Scanner;

public class SocketSamp {
    public static void main(String[] args) {
        try {
            //samp1();
            samp2(args);
            getIPAddress();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void samp1() throws IOException {
        String host = "time-a.nist.gov";
        int port = 13;
        int timeout = 10000;
        try (var s = new Socket(host,port);
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

    //获取IP地址
    public static void getIPAddress(){
        try{
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()){
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()){
                    InetAddress ip = (InetAddress) addresses.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && ip.getHostAddress().indexOf(":") == -1){
                        System.out.println("本机的IP = " + ip.getHostAddress());
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


