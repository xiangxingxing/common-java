package com.levi.basic.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
java.io.FileOutputStream
    1.文件字节输出流：负责写
    2.字节的方式，完成写的操作（内存 --> 硬盘）
    3.输出流需刷新flush
*/

public class FileOutputStreamSamp {

    public static void main(String[] args) throws IOException{
        Samp1();
        //copyFile("/Users/xiangxx/Downloads/DownloadFile/chicken.png");
    }

    public static void Samp1() throws IOException{
        //文件不存则创建
        //谨慎使用‼️先清空源文件，再写入数据
        FileOutputStream outputStream = new FileOutputStream("levi.txt", true);

        //byte[] bytes = {97,98,99,100};
        String str = "tes冠军！";
        byte[] bytes = str.getBytes();
        outputStream.write(bytes);
        System.out.println("Append done!");
    }

    //结合 FileInputStream + FileOutputStream
    //实现文件的拷贝
    public static void copyFile(String filePath){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        String outputPath = "/Users/xiangxx/Downloads/DownloadFile/copied.png";
        try {
            byte[] bytes = new byte[1024 * 1024]; // 1MB
            fis = new FileInputStream(filePath);
            fos = new FileOutputStream(outputPath);
            int length;
            while ((length = fis.read(bytes)) != -1){
                fos.write(bytes, 0, length);
            }

            //输出流需要flush更新
            fos.flush();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
