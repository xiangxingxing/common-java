package com.levi.basic.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathSamp {
    private static final String targetDir = "/Users/xiangxx/Buaa/IdeaProject/ForTest";
    private static final String sourceDir = "/Users/xiangxx/Buaa/论文";

    public static void main(String[] args) {
        //samp1();
        Path source = Paths.get(sourceDir);
        Path target = Paths.get(targetDir);
        samp2(source,target);
    }

    public static void samp1(){
        Path p = Paths.get("com.levi.basic.reflect.Student");
        Path absolutePath = p.toAbsolutePath();
        System.out.println(absolutePath);

        Path temp = absolutePath.resolveSibling("temp");//: /Users/xiangxx/Buaa/IdeaProject/java-levi/temp
        System.out.println(temp);
    }

    /*
     * 目录拷贝2:使用Path和Files实现
     * */
    public static void samp2(Path source, Path target){
        try {
            Files.walk(source).forEach(p -> {
                try {
                    Path q =  target.resolve(source.relativize(p));
                    if (Files.isDirectory(p)){
                        Files.createDirectory(q);
                    }
                    else {
                        Files.copy(p,q);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
