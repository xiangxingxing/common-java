package com.levi.basic.thread;

public class MyRunnable2 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "--->  begin sleep.");
        //只能tryCatch，因为子类不能比父类抛出更多的异常
        try {
            Thread.sleep(365 * 24 * 60 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "--->  end sleep.");
    }
}
