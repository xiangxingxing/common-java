package com.levi.basic.thread;


/*
* 多线程
*   启动：start()启动新线程，分配新的分支栈空间
*        run()不会启动新线程，也不会分配新的分支栈
*
*   实现线程的第一种方式：编写新类直接继承java.lang.thread，重写run方法
*   实现线程的第二种方式【常用，可以继承其他的类】：编写新类直接继承java.lang.Runnable接口，实现run方法，作为实参传入Thread构造器里
*
*
*   线程的生命周期：新建、就绪、运行、阻塞、终止
*
* */

public class ThreadSamp {

    public static void main(String[] args) {
        //Samp1();
        Samp2();
    }

    public static void Samp1(){
        Thread thread = new Thread(new MyRunnable());
        thread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("main branch : " + i);
        }
    }

    //匿名内部类
    public static void Samp2(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("partial branch : " + i);
                }
            }
        });
        thread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("main branch : " + i);
        }
    }
}
