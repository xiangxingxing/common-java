package com.levi.basic.thread;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private static final Object object = new Object();

    public Account(String account, double balance) {
        this.account = account;
        this.balance = balance;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    private String account;
    private double balance;

    // 取款
    // 需使多个线程同步处理
    // synchronized参数:多线程共享的对象
    // synchronized可以放在void之前，锁的是this，但是不灵活，且表示整个方法都需要同步，可能无故扩大同步的范围，导致执行效率低
    /*public void withdraw(double money){
        //synchronized (new Object())  不能同步局部变量、null对象
        //synchronized ("abc") 常量池
        synchronized (object){
            double before = this.getBalance();
            double remained = before - money;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            this.setBalance(remained);
            System.out.println(Thread.currentThread().getName() + "对" + this.getAccount() + "取走了" + money +
                    ",余额为" + this.getBalance());
        }

    }*/

    ReentrantLock lck = new ReentrantLock();

    public void withdraw(double money){
        //synchronized (new Object())  不能同步局部变量、null对象
        //synchronized ("abc") 常量池

        lck.lock();
        double before = this.getBalance();
        double remained = before - money;
//           try {
//               Thread.sleep(1000);
//           } catch (InterruptedException e) {
//               e.printStackTrace();
//           }
        this.setBalance(remained);
        System.out.println(Thread.currentThread().getName() + "对" + this.getAccount() + "取走了" + money +
                    ",余额为" + this.getBalance());
        lck.unlock();
        }

}
