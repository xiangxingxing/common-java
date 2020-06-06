package com.levi.basic.collections;

import java.util.*;

public class CollectionSamp {
    public static void main(String[] args) {
        //Samp1();
        //Samp2();
        //Samp3();
        //Samp4();
        Samp5();

        //Collections.sort(list);//该list存储的类型需要实现Comparable<T>类型

    }

    //关于remove
    //移除数组元素不能用foreach，应利用集合的迭代器
    public static void Samp5(){
        List list = new ArrayList<>();
        //变成线程安全
        //Collections.synchronizedList(list);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        //wrong
//        for (Object item : list){
//            if(item.equals(1)){

//                list.remove(item); //通过集合删除，没有通知迭代器；删除之后，迭代器没有及时更新；
//            }
//        }

        //right
        //‼️集合结构如果发生改变，迭代器必须重新获取‼️
/*

        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            Object item = iterator.next();
            if (item.equals(2)){
                iterator.remove();//使用迭代器来删除当前指向的元素
            }
        }
*/


        //删除满足某个条件的元素
        boolean b = list.removeIf(e -> e.equals(5));
        System.out.println("删除值为2的元素结果 = " + b);

        for (Object item : list){
            System.out.println(item);
        }

//        iterator = list.iterator();
//        iterator.forEachRemaining(System.out :: println);
    }

    public static void Samp1(){
        Collection c = new ArrayList();
        //添加的为对象地址
        c.add(new Object());
        c.add(true);
        c.add(100);
        c.add(1.34);
        c.add("levi");

        for (Object item : c) {
            System.out.println(item);
        }

        //获取集合中元素的个数：size()
        System.out.println("集合c中元素的个数：" + c.size());

        //清空集合 clear
        //包含元素 contains(Object o)
        System.out.println("是否包含levi字符串："+ c.contains("levi"));

        //移除某个元素 remove(Object o)
        //判断集合是否为空 isEmpty()
        System.out.println("集合是否为空：" + c.isEmpty());

        //toArray() 转成数组

    }

    /*
    * 迭代器：所有collection的通用方式
    * */
    public static void Samp2(){
        //HashSet 无序不重复
        Collection collection = new HashSet();
        collection.add(400);
        collection.add(43);
        collection.add(43);
        collection.add(600);
        collection.add(10);
        collection.add(590);
        collection.add(4);
        collection.add(230);

        Iterator itr = collection.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }
    }

    /*
    * contains(Object o)方法，底层调用了o.equals()方法
    * */
    public static void Samp3(){
        Collection collection = new ArrayList();
        String s1 = new String("abc");
        String s2 = new String("def");

        collection.add(s1);
        collection.add(s2);

        String str = new String("abc");
        System.out.println("是否包含str元素:" + collection.contains(str));
    }

    /*
        ‼️存放在集合中的元素，需要重新equals()方法，不重写的话用的是'=='，比较对象内存地址‼️
    * */
    public static void Samp4(){
        Collection col = new ArrayList();
        Person p1 = new Person("levi");
        Person p2 = new Person("json");
        col.add(p1);
        col.add(p2);

        Person p3 = new Person("levi");
        //Person没有重写equals方法则false
        //重写了则为true
        System.out.println(col.contains(p3));
    }

}

class Person{
    public String getName() {
        return name;
    }

    public Person(String name){
        this.name = name;
    }

    private String name;

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Person)){
            return false;
        }

        if (obj == this){
            return true;
        }

        Person p = (Person) obj;
        return p.name.equals(this.name);
    }
}
