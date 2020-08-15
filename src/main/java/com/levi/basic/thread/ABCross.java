package com.levi.basic.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABCross implements Runnable{
    // 打印次数
    private static final int COUNT = 3;
    //锁
    private static final Lock lock = new ReentrantLock();
    // 下一个Condition
    private final Condition next ;
    // 当前Condition
    private final Condition now ;
    // 打印的字符
    private char ch;

    public ABCross(Condition now, Condition next, char ch) {
        this.next = next;
        this.now = now;
        this.ch = ch;
    }


    public static void main(String[] args) throws InterruptedException {
        //打印A线程的condition
        Condition a = lock.newCondition();
        //打印B线程的condition
        Condition b = lock.newCondition();
        //打印C线程的condition
        Condition c = lock.newCondition();

        new Thread(new ABCross(a, b, 'A'),"线程1 ：").start();
        TimeUnit.MILLISECONDS.sleep(10);
        new Thread(new ABCross(b, c, 'B'),"线程2 ：").start();
        TimeUnit.MILLISECONDS.sleep(10);
        new Thread(new ABCross(c, a, 'C'),"线程3 ：").start();
        TimeUnit.MILLISECONDS.sleep(10);

    }

    @Override
    public void run() {

        try {
            // 获取打印锁 进入临界区
            lock.lock();

            for (int i = 0; i < COUNT; i++) {
                // 获取当前锁的
                next.signal();
                System.out.print(Thread.currentThread().getName() + ch + " ");
                if ((COUNT - 1) > i) {
                    // 当前线程让出锁并等待唤醒
                    now.await();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

