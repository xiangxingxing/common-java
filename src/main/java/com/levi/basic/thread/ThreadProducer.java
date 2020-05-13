package com.levi.basic.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadProducer {
    public static void main(String[] args) {
        List list = new ArrayList();

        Thread t1 = new Thread(new Producer(list));
        Thread t2 = new Thread(new Consumer(list));

        t1.setName("生产者");
        t2.setName("消费者");

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
        while (true) {
            synchronized (collection) {
                if (collection.size() > 2) {
                    try {
                        collection.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Object obj = new Object();
                    collection.add(obj);
                    System.out.println(Thread.currentThread().getName() + "生产了一个对象：" + obj);
                    collection.notify();
                }
            }
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