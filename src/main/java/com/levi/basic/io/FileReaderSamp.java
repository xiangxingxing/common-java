package com.levi.basic.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
    对象：普通文本文件
*/
public class FileReaderSamp {
    public static void main(String[] args) {
        readTextFile("levi.txt");
    }

    public static  void readTextFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
            char[] chars = new char[4];
            int readCount;
            while ((readCount = fileReader.read(chars)) != -1){
                System.out.println(new String(chars,0, readCount));;
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
