package com.levi.basic.thread2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecutorDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        try (var in = new Scanner(System.in)){
            System.out.print("Enter base directory (e.g. /opt/jdk-9-src): ");
            String start = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String word = in.nextLine();

            Set<Path> files = descendants(Path.of(start));
            var tasks = new ArrayList<Callable<Long>>();
            for (Path file : files){
                Callable<Long> task = () -> occurrences(word, file);
                tasks.add(task);
            }

            //ExecutorService executor = Executors.newCachedThreadPool();
            ExecutorService executor = Executors.newSingleThreadExecutor(); //测速对比多线程
            Instant startTime = Instant.now();
            List<Future<Long>> results = executor.invokeAll(tasks);
            long total = 0;
            //这个工作会阻塞，直到所有结果都可用
            for (Future<Long> result : results){
                total += result.get();
            }

            Instant endTime = Instant.now();
            System.out.println("Occurrences of " + word + " = " + total);
            System.out.println("Time elapsed:" + Duration.between(startTime, endTime).toMillis() + "ms");

            var searchTasks = new ArrayList<Callable<Path>>();
            for (Path file : files){
                searchTasks.add(searchForTask(word, file));
            }

            //并行化搜索
            Path found = executor.invokeAny(searchTasks);
            System.out.println(word + " occurs in: " + found);

            if (executor instanceof ThreadPoolExecutor) //the single thread executor isn't
            {
                System.out.println("Largest pool size: " + ((ThreadPoolExecutor) executor).getLargestPoolSize());
            }

            executor.shutdown();

        }
    }

    //指定单词在一个文件中的出现次数
    public static long occurrences(String word, Path path) {
        try (var in = new Scanner(path)) {
            int count = 0;
            while (in.hasNext()) {
                if (in.next().equals(word)) {
                    count++;
                }
            }

            return count;
        } catch (IOException e) {
            return 0;
        }
    }

    public static Set<Path> descendants(Path rootDir) throws IOException{
        try (Stream<Path> entries = Files.walk(rootDir)){
             //entries.collect(Collectors.toSet());
            return entries.filter(Files::isRegularFile).collect(Collectors.toSet());
        }
    }

    //产生一个任务:搜索包含指定单词的第一个文件
    public static Callable<Path> searchForTask(String word, Path path){
        return () -> {
            try (var in = new Scanner(path)){
                while (in.hasNext()){
                    if (in.next().equals(word)){
                        return path;
                    }

                    if (Thread.currentThread().isInterrupted()){
                        System.out.println("Search in " + path + " canceled");
                        return null;
                    }
                }

                throw new NoSuchElementException();
            }
        };
    }


}
