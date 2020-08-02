package com.levi.basic.thread;

public class PrintNumber {
    public static void main(String[] args) {
        Number num = new Number(1);
        Thread t1 = new Thread(new ThreadOne(num));
        Thread t2 = new Thread(new ThreadTwo(num));

        t1.setName("thread one");
        t2.setName("thread two");

        t1.start();
        t2.start();
    }
}

class Number{
    public Number(int value) {
        this.value = value;
    }

    int value;
}

class ThreadOne implements Runnable{
    private Number number;

    public ThreadOne(Number number) {
        this.number = number;
    }

    @Override
    public void run() {
        while (true){
            synchronized (number){
                if(number.value == 1){
                    System.out.println(Thread.currentThread().getName() + ": " + number.value);
                    number.value = 2;
                    number.notify();
                }
                else {
                    try {
                        number.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class ThreadTwo implements Runnable{
    private Number number;

    public ThreadTwo(Number number) {
        this.number = number;
    }


    @Override
    public void run() {
        while (true){
            synchronized (number){
                if(number.value == 2){
                    System.out.println(Thread.currentThread().getName() + ": " + number.value);
                    number.value = 1;
                    number.notify();
                }
                else {
                    try {
                        number.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
