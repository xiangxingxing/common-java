package com.levi.basic.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/*
    对象：普通文本文件
*/
public class FileReaderSamp {
    public static void main(String[] args) {
        //readTextFile("levi.txt");

        String path = Thread.currentThread().getContextClassLoader()
                .getResource("common.properties").getPath();
        readProperties(path);
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

    public static  void readProperties(String filePath) {
        FileReader reader;
        try {
            reader = new FileReader(filePath);
            //创建属性类对象
            Properties properties = new Properties();
            //加载
            properties.load(reader);
            reader.close();

            //通过key获取value
            String value = properties.getProperty("class");

            //反射机制
            Class<?> aClass = Class.forName(value);
            Object o = aClass.newInstance();
            System.out.println(o);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
