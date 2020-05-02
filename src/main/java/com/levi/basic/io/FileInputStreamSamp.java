package com.levi.basic.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
java.io.FileInputStream
    1.文件字节输入流：万能，任何类型的文件均能读取
    2.字节的方式，完成读的操作（硬盘 --> 内存）

*/
public class FileInputStreamSamp {
    public static void main(String[] args) {
        try {
            Samp1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Samp1() throws IOException {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream("levi.txt");
            //开始读取
            //idea默认的路径为project工程路径下
            byte[] bytes = new byte[4];

            //方法一
            /*while (true){
                int readCount = stream.read(bytes);
                if (readCount == -1){
                    break;
                }

                System.out.print(new String(bytes,0, readCount));
            }*/

            //方法二
           /* int readCount = 0;
            while ((readCount = stream.read(bytes)) != -1){
                System.out.print(new String(bytes,0, readCount));
            }*/

           //方法三 不适合大文件，因为byte数组不能太大
            // stream.available():表示剩余未读的字节数
            /*byte[] newByte = new byte[stream.available()];
            System.out.print(new String(newByte));*/
            //System.out.println(stream.available());

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        finally {
            if (stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
