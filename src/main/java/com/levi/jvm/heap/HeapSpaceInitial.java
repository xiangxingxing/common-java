package com.levi.jvm.heap;

/**
 *  1.设置堆空间大小
 *  -Xms 用来设置堆空间（老年代 + 年轻代）的初始内存大小
 *      -X 是JVM运行参数
 *      -ms 是memory start
 *  -Xmx 设置堆空间最大内存大小
 *
 *  2.默认堆空间的大小
 *      初始内存大小：物理内存大小 / 64
 *      最大内存大小：物理电脑内存大小 / 4
 *
 *  3.手动设置：-Xms  -Xmx
 *      开发中建议设置成两值相同
* */
public class HeapSpaceInitial {
    public static void main(String[] args) {
        //Java虚拟机中堆内存的总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        //返回java虚拟机试图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 /1024;

        System.out.println("-Xms : " + initialMemory + "M");
        System.out.println("-Xmx : " + maxMemory + "M");

        System.out.println("系统内存大小为 " + initialMemory * 64.0 / 1024 + "G");
        System.out.println("系统内存大小为 " + maxMemory * 4.0 / 1024 + "G");

    }

}
