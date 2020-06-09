package com.levi.basic.collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/*
* 1.RandomAccess接口不包含任何方法，用来测试一个特定的集合是否支持高效的随机访问
* */

public class CollectionTest {
    public static void main(String[] args){
        linkedListTest();

    }

    /*
    * 链表
    * Iterator : 删除元素
    * ListIterator : 添加元素
    */
    public static void linkedListTest(){
        var a = new LinkedList<String>();
        a.add("Amy");
        a.add("Carl");
        a.add("Erica");

        var b = new LinkedList<String>();
        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");

        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        //将b并到a中
        while (bIter.hasNext()) {
            if (aIter.hasNext()) {
                aIter.next();
            }

            aIter.add(bIter.next());
        }
        System.out.println("NO.1:" + a);

        //移除b链表中第偶数位的元素
        bIter = b.iterator();
        while (bIter.hasNext()) {
            bIter.next();//先next()后，再移除刚才访问的数据
            if (bIter.hasNext()) {
                bIter.next();
                bIter.remove();
            }
        }

        System.out.println("NO.2:" + b);

        //从a中移除b中的所有元素
        a.removeAll(b);
        System.out.println("NO.3:" + a);
    }


}
