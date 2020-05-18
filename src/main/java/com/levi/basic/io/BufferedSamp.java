package com.levi.basic.io;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
*   BufferedReader(Reader in)：自带缓冲区的字符输入流（包装流），自带缓冲；
*
* */
public class BufferedSamp {
    public static void main(String[] args) {
        //samp1();
        //samp2();
        //samp3();
        samp4("level1");
        samp4("level2");
        samp4("level3");
    }

    //BufferedReader
    public static void samp1(){
        BufferedReader br = null;
        try {
            FileReader reader = new FileReader("levi.txt");//节点流
            br = new BufferedReader(reader);//包装流、处理流
            //读取
            String str;
            while ((str = br.readLine()) != null){
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (br != null){
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
            while ((str = br.readLine()) != null){
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (br != null){
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
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("levi.txt",true)));
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
    //不需要手动close标展输出流
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
}
