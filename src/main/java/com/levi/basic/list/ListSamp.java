package com.levi.basic.list;

import java.util.ArrayList;
import java.util.List;

/*
* list
*       1.有序可重复
*       2.特有的方法
*           void add(int index, E element)
*           E get(int index)
*           int indexOf(Object o)
*           int lastIndexOf(Object o)
*           E remove(int index)
*           E set(int index, E element)
* */
public class ListSamp {

    public static void main(String[] args) {
        Samp1();
    }

    public static void Samp1(){
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(4);
        list.add(6);
        list.add(8);

        //指定位置添加
        list.add(3,10);

        //获取索引元素
        System.out.println(list.get(3));

    }
}
