package com.levi.basic.thread;


/*
* 多线程
*   启动：start()启动新线程，分配新的分支栈空间
*        run()不会启动新线程，也不会分配新的分支栈
*
*   实现线程的方式
*           1.编写新类直接继承java.lang.thread，重写run方法
*           2.【常用，可以继承其他的类】：编写新类直接继承java.lang.Runnable接口，实现run方法，作为实参传入Thread构造器里
*           3.实现callable接口(jdk8新特性)，可以获取线程的返回值；Thread配合FutureTask
*
*
*   线程的生命周期：新建、就绪、运行、阻塞、终止
*
*   获取当前线程对象：Thread.currentThread()
*
*   睡眠Thread.sleep(1000)
*       让当前线程休眠，进入阻塞状态；【t.sleep()相当于所在线程调用Thread.sleep()，和t没有关系】
*
*   线程安全问题：
*               1.多线程并发
*               2.有共享数据
*               3.共享数据有修改的行为
*
*   Java三大变量：
*               1.实例变量【堆 --> 线程共享 --> 可能存在线程安全问题】
*               2.静态变量【方法区 --> 线程共享 --> 可能存在线程安全问题】
*               3.局部变量【栈 --> 线程不共享 --> 不存在线程安全问题
*
*   synchronized写法
*               1.同步代码块 synchronized(线程共享对象){}
*               2.在实例方法上使用synchronized，表示共享对象一定是this
*               3.在静态方法上使用synchronized，表示找类锁，不管类有多少个对象，也就只有一个类锁
*
*   同步方案
*           1.尽量使用局部变量代替实例变量和静态变量
*           2.如果必须是实例变量，则考虑创建多个对象，这样实例变量的内存就不共享了
*           3.synchronized
*
*   守护线程:主线程结束后，守护线程会自动结束
*           start启动线程前将线程设置为守护线程 t.setDaemon(true);
*
*   定时器 Timer (spring --> SpringTask框架)
*
*   Object的wait和notify方法 建立在synchronized线程同步的基础上
*           O.wait()：让正在O对象上活动的线程进入等待状态，释放o对象锁，直到被唤醒为止
*           O.notify():唤醒正在O对象上等待的线程
* */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadSamp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Samp1();
        //Samp2();
        //Samp3();
        //Samp4();
        //Samp5();
        //samp6();
        //samp7();
        samp8();
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

    //sleep
    public static void Samp3(){
        for (int i= 0; i< 10;i++){
            System.out.println("----> "+ i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //中断睡眠
    public static void Samp4(){
        Thread thread = new Thread(new MyRunnable2());
        thread.setName("Snoopy");
        thread.start();
        //计时五秒
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //结束睡眠
        thread.interrupt();
    }

    //优先级和join合并线程
    public static void Samp5(){
        System.out.println("最高优先级:"+ Thread.MAX_PRIORITY);
        System.out.println("最小优先级:"+ Thread.MIN_PRIORITY);
        Thread t = new Thread(new MyRunnable());
        t.start();
        try {
            t.join();//当前线程阻塞，直到t线程执行完
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("默认优先级:"+ Thread.NORM_PRIORITY);
    }

    //synchronized同步
    public static void samp6(){
        Account account = new Account("levi", 1000);
        MyThread t1 = new MyThread(account);
        t1.setName("t1");
        MyThread t2 = new MyThread(account);
        t2.setName("t2");

        t1.start();
        t2.start();

    }

    //定时器
    public static void samp7(){
        //创建定时器对象
        Timer timer = new Timer();
        //Timer timer = new Timer(true); // 守护线程的方式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date firstTime = null;
        try {
            firstTime = sdf.parse("2020-5-11 11:30:30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        timer.schedule(new LogTimerTask(), firstTime, 1000 * 10);
    }

    public static void samp8() throws ExecutionException, InterruptedException {
        /*
         * 使用Callable来创建线程
         */
        Callable <Integer> aCallable = new MyCallable<>(10);

        FutureTask<Integer> task = new FutureTask<>(aCallable);

        Thread aThread = new Thread(task);

        aThread.start();

        Integer integer = task.get();
        System.out.println(integer);
    }

}

class MyThread extends Thread {
    private Account act;
    public MyThread(Account account) {
        this.act = account;
    }

    public void run() {
        double money = 500;
        act.withdraw(money);
    }
}

class LogTimerTask extends TimerTask{

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String firstTime = null;

        firstTime = sdf.format(new Date());
        System.out.println(firstTime + "：成功执行了一次");

    }
}

/*
 * Callable接口实现多线程Demo
 */
class  MyCallable<V> implements Callable<V>
{
    private V result;
    public MyCallable(V result){
        this.result = result;
    }

    @Override
    public V call() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("I am Callable thread : "+Thread.currentThread().getName());
        return result;
    }

}
