package com.levi.basic.thread;

public class NotifyWait {
    public static void main(String[] args) {
        Num num = new Num(0);
        Thread t1 = new Thread(new ThreadOdd(num),"odd");
        Thread t2 = new Thread(new ThreadEven(num), "even");

        t1.start();
        t2.start();
    }
}


class Num {
    int number;

    public Num(int num) {
        number = num;
    }
}

class ThreadOdd implements Runnable {

    private Num numObj;

    public ThreadOdd(Num num) {
        numObj = num;
    }

    @Override
    public void run() {
        while (numObj.number < 20){
            synchronized (numObj){
                if((numObj.number & 1) == 0){
                    try {
                        numObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println(Thread.currentThread().getName() +"  number = "+ numObj.number);
                    numObj.number++;
                    numObj.notify();
                }
            }
        }
    }
}

class ThreadEven implements Runnable {

    private Num numObj;

    public ThreadEven(Num num) {
        numObj = num;
    }

    @Override
    public void run() {
        while (numObj.number < 20){
            synchronized (numObj){
                if((numObj.number & 1) == 1){
                    try {
                        numObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println(Thread.currentThread().getName()  +" number = "+ numObj.number);
                    numObj.number++;
                    numObj.notify();
                }
            }
        }
    }
}


