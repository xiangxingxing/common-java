package com.levi.basic.thread2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CHMDemo {
    public static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

    public static void process(Path file){
        try (var in = new Scanner(file)) {
            while (in.hasNext()){
                String word = in.next();
                map.merge(word, 1L, Long :: sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Path> descendants(Path rootDir) throws IOException{
        try (Stream<Path> entries = Files.walk(rootDir)){
            return entries.collect(Collectors.toSet());
        }
    }

    public static void main(String[] args)
        throws InterruptedException, IOException {
        int processors = Runtime.getRuntime().availableProcessors();//获取处理器内核数
        /*
        *   ‼️为了得到最优的运行速度，并发线程数等于处理器内核数‼️ --> 使用固定线程池 newFixedThreadPool()
        * */
        ExecutorService executor = Executors.newFixedThreadPool(processors);
        Path pathRoot = Path.of(".");
        for (Path p : descendants(pathRoot)){
            if (p.getFileName().toString().endsWith("java")){
                executor.execute(() -> process(p));
            }
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
//        map.forEach((k,v) -> {
//            if( v > 10 ){
//                System.out.println(k + " occurs " + v + " times.");
//            }
//        });

        /*
        *   参数化阈值
        *   1.如果映射包含的元素多于这个阈值，就会并行完成批操作
        *   2.若希望批操作在一个线程中运行，则使用阈值 = Long.MAX_VALUE
        * */
        map.forEach(Long.MAX_VALUE,(k, v) ->
        {
            if ( v > 100){
                return k + " -> " + v + " times computed by " + Thread.currentThread().getId();
            }
            return null;
        }, System.out::println);


    }
}
