package com.levi.basic.io;

import java.io.*;
import java.nio.file.Path;

/*
 * File类
 *       1.File类和四大家族么关系，所以File类不能完成文件的读和写
 *       2.表示文件和目录路径名的抽象形式
 *
 * */
public class FileSamp {
    private static final String desDic = "/Users/xiangxx/Buaa/IdeaProject/ForTest";
    private static final String srcDic = "/Users/xiangxx/Buaa/论文";
    private static final String testDic = "/Users/xiangxx/Buaa/论文/a/b";

    public static void main(String[] args) {
        //samp1();
        samp2();

        // -->  a/b
        //System.out.println(testDic.replace(srcDic, "").substring(1));
    }

    public static void samp1() {
        File fl = new File("/Users/xiangxx/Buaa/IdeaProject/ForTest/file");
/*
        try {
            //是否存在
            if (!file.exists()){
                file.createNewFile();//以文件的形式创建
                file.mkdir();//以目录的形式创建
                file.mkdirs();//以多重目录的形式创建
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        File file = new File(srcDic);
        System.out.println(file.getName());
/*        System.out.println(file.getAbsolutePath());

        File f2 = new File(".");//idea当前路径
        File[] files = f2.listFiles();
        for (File f : files){
            System.out.println(f.getName());
        }
        */
    }

    /*
    *       目录拷贝
    * */

    public static void samp2() {
        //源目标
        File srcFile = new File(srcDic);
        //目的目标
        File desFile = null;
        String desHead = desDic;
        if (srcFile.isDirectory()) {
            String srcFileName = srcFile.getName();
            desHead = getFullPath(desDic, srcFileName);
        }
        desFile = new File(desHead);
        if (!desFile.exists()) {
            desFile.mkdirs();
        }

        //拷贝方法
        copyDirectory(srcFile, desFile);

    }

    private static void copyDirectory(File srcFile, File desFile) {
        if (srcFile.isFile()) {
            //是文件时边读边拷贝
            FileInputStream fi = null;
            FileOutputStream fo = null;

            try {
                fi = new FileInputStream(srcFile);
                String to = getDesPath(desFile, srcFile);
                fo = new FileOutputStream(to);

                byte[] bytes = new byte[1024 * 1024];
                int readCount = 0;
                while ((readCount = fi.read(bytes)) != -1) {
                    fo.write(bytes, 0, readCount);
                }

                fo.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fi != null) {
                    try {
                        fi.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fo != null) {
                    try {
                        fo.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return;
        }

        File[] files = srcFile.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                String desPath = getDesPath(desFile, f);
                File newFile = new File(desPath);
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }
            }

            copyDirectory(f, desFile);
        }
    }

    private static String getFullPath(String path, String fileName) {
        return path.endsWith("/") ? path : (path + "/") + fileName;
    }

    private static String getDesPath(File desFile, File f) {
        return getFullPath(desFile.getAbsolutePath(),
                f.getAbsolutePath().replace(srcDic, "").substring(1));
    }
}
