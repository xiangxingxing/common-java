package com.levi.basic.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadProducer {
    public static void main(String[] args) {
        List list = new ArrayList();

        Thread t1 = new Thread(new Producer(list), "生产者");
        Thread t2 = new Thread(new Consumer(list), "消费者");
        t1.start();
        t2.start();
    }
}


class Producer implements Runnable {

    private List collection;

    public Producer(List list) {

        this.collection = list;
    }

    //生产者
    @Override
    public void run() {
        int n = 10;
        while (n > 0) {
            synchronized (collection) {
                if (collection.size() > 0) {
                    try {
                        collection.wait();//进入等待状态,并释放对象锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Object obj = new Object();
                    collection.add(obj);
                    System.out.println(Thread.currentThread().getName() + "生产了一个对象：" + obj);
                    /*
                    * notify()方法会随机叫醒一个正在等待的线程，而notifyAll()会叫醒所有正在等待的线程。
                    *【注】这个时候线程B并没有释放锁lock,除非线程B这个时候使用lock.wait()释放锁，或者线程B执行结束自行释放锁
                    * */
                    collection.notify();
                }
            }
            n--;
        }
    }
}

class Consumer implements Runnable {

    private List collection;

    public Consumer(List list) {

        collection = list;
    }

    //消费者
    @Override
    public void run() {
        while (true) {
            synchronized (collection) {
                if (collection.size() == 0) {
                    try {
                        collection.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Object obj = collection.remove(0);
                    System.out.println(Thread.currentThread().getName() + "消费了一个对象：" + obj);
                    collection.notify();
                }
            }
        }
    }
}