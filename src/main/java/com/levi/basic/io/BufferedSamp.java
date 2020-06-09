package com.levi.basic.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 *   BufferedReader(Reader in)：自带缓冲区的字符输入流（包装流），自带缓冲；
 *
 * */
public class BufferedSamp {
    public static void main(String[] args){
        //samp1();
        //samp2();
        //samp3();
        //samp4("level1");
        //samp4("level2");
        //samp4("level3");
        //bufferSamp();
        //bufferSamp2();
        //bufferSamp3();
        channelSamp1();
    }

    //BufferedReader
    public static void samp1(){
        BufferedReader br = null;
        try {
            FileReader reader = new FileReader("levi.txt");//节点流
            br = new BufferedReader(reader);//包装流、处理流
            //读取
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //字符流转换为字节流
    public static void samp2(){
        BufferedReader br = null;
        try {
//            FileInputStream stream = new FileInputStream("levi.txt");//节点流
//            InputStreamReader streamReader = new InputStreamReader(stream);
//            br = new BufferedReader(streamReader);//包装流、处理流

            br = new BufferedReader(new InputStreamReader(new FileInputStream("levi.txt")));

            //读取
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //写入流
    public static void samp3(){
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("levi.txt", true)));
            bw.write('\n');
            bw.write(new Date().toString());
            bw.write("By xxx.");

            bw.flush();//刷新
            bw.close();//关闭最外层
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //标准输出流【System.Out 日志文件的底层】
    //不需要手动close标准输出流
    public static void samp4(String msg){
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream("log.txt", true));
            System.setOut(ps);//修改输出的方向

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM--dd HH:mm:ss SSS");
            String str = sdf.format(date);
            ps.println(str + ":" + msg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //ByteBuffer 字节缓冲的使用
    /*
     * 输出流
     * */
    public static void bufferSamp(){

        //创建一个输出流 channel
        //真实类型是FileChannelImpl
        try (FileChannel fileChannel = new FileOutputStream("levi.txt", true).getChannel()) {
            String str = "\nGo home." + new Date();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            byteBuffer.put(str.getBytes());

            byteBuffer.flip();//反转

            //将byteBuffer数据写入到fileChannel
            fileChannel.write(byteBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
     * 输入流
     * */
    public static void bufferSamp2(){
        //创建文件的输入流
        File file = new File("levi.txt");
        try (FileInputStream inputStream = new FileInputStream(file)) {

            //获取输入流的Channel
            FileChannel channel = inputStream.getChannel();

            //创建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

            //将channel中的数据读入缓冲区
            channel.read(byteBuffer);

            //打印
            System.out.println(new String(byteBuffer.array()));


            //关闭流

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 使用一个buffer完成文件的拷贝
     *
     * */
    public static void bufferSamp3(){
        //输入、输出流
        try (FileInputStream fis = new FileInputStream("levi.txt");
             FileOutputStream fos = new FileOutputStream("copiedByBuffer.txt");

             //获取通道
             FileChannel fisChannel = fis.getChannel();
             FileChannel fosChannel = fos.getChannel()) {


            //准备一个Buffer
            /*
             * 1.在循环时必须clear
             * 2.注意反转方向flip()
             */

            ByteBuffer buffer = ByteBuffer.allocate(512);

            while (true) {
                buffer.clear();//防止未读完，position和limit相等 -> readCount始终为0
                //buffer从channel读入数据
                int readCount = fisChannel.read(buffer);
                if (readCount == -1) {
                    break;
                }

                buffer.flip();

                //buffer数据写入channel
                fosChannel.write(buffer);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 使用Channel的transferFrom方法实现文件的拷贝
     *
     * */
    public static void channelSamp1(){
        try (FileInputStream fis = new FileInputStream("/Users/xiangxingxing/Downloads/ssr.jpeg");
             FileOutputStream fos = new FileOutputStream("/Users/xiangxingxing/rss.jpeg");

             //获取通道
             FileChannel sourceChannel = fis.getChannel();
             FileChannel desChannel = fos.getChannel()) {

            desChannel.transferFrom(sourceChannel, 0 ,sourceChannel.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
