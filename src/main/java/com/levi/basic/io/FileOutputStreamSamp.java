package com.levi.basic.io;

import java.io.FileOutputStream;
import java.io.IOException;

/*
java.io.FileOutputStream
    1.文件字节输出流：负责写
    2.字节的方式，完成写的操作（内存 --> 硬盘）
    3.输出流需刷新flush
*/

public class FileOutputStreamSamp {

    public static void main(String[] args) {
        try {
            Samp1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Samp1() throws IOException{
        //文件不存则创建
        //谨慎使用‼️先清空源文件，再写入数据
        FileOutputStream outputStream = new FileOutputStream("levi.txt", true);

        byte[] bytes = {97,98,99,100};
//        String str = "tes冠军！";
//        byte[] bytes = str.getBytes();
        outputStream.write(bytes);
        System.out.println("Append done!");
    }
}
