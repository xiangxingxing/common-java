package com.levi.basic;

import com.levi.thread.MyInter;

public class StaticConstructor implements MyInter {
    public StaticConstructor() {
        System.out.print("默认构造方法！--");
    }

    //非静态代码块
    {
        System.out.print("非静态代码块！--");
    }

    //静态代码块
    static {
        System.out.print("静态代码块！--");
    }

    public static void test() {
        System.out.print("static void test --");

    }

    @Override
    public void Get() {

    }
}
