package com.levi.basic.collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/*
* 涉及到比较器问题
* */
public class TreeSetSamp {
    public static void main(String[] args) {
        //Samp1();
        //Samp2();
        Samp3();
    }

    public static void Samp1(){
        TreeSet<Cat> treeSet = new TreeSet<>();
        treeSet.add(new Cat("coffee",1));
        treeSet.add(new Cat("coffee",10));
        treeSet.add(new Cat("dora",5));

        for (Cat cat : treeSet){
            System.out.println(cat);
        }
    }

    public static void Samp2(){
        TreeSet<WuGui> ts = new TreeSet<>(new WuGuiComparator());
        ts.add(new WuGui("d"));
        ts.add(new WuGui("e"));
        ts.add(new WuGui("a"));
        ts.add(new WuGui("b"));

        for (WuGui wg : ts){
            System.out.println(wg);
        }
    }

    public static void Samp3(){
        /*
            匿名方式:直接new接口
        * */
        //等价于：TreeSet<Dog> ts = new TreeSet<>(Comparator.comparing(o -> o.name));
        TreeSet<Dog> ts = new TreeSet<>((o1, o2) -> o2.name.compareTo(o1.name));

        ts.add(new Dog("t"));
        ts.add(new Dog("z"));
        ts.add(new Dog("b"));
        ts.add(new Dog("a"));

        for (Dog dog : ts){
            System.out.println(dog);
        }

        Iterator<Dog> tor = ts.iterator();
        Dog first = tor.next();
        tor.remove();
        System.out.println("===== remove first ======");
        ts.forEach(System.out::println);

        System.out.println("===== add new ======");
        ts.add(new Dog("c"));
        ts.forEach(System.out::println);
    }
}
