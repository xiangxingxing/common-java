package com.levi.basic.io;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterSamp {
    public static void main(String[] args) {
        writeFile();
    }

    public static void writeFile(){
        FileWriter writer = null;
        try {
            //创建
            //writer = new FileWriter("levi.txt");
            writer = new FileWriter("levi.txt", true);

            //执行
            String str = "\nBy levi.";
            writer.write(str);

            //刷新
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
