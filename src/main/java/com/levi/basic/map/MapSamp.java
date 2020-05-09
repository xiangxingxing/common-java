package com.levi.basic.map;

import java.util.*;

/*
* java.util.Map接口中常用的方法
*       1.map和collection没有继承关系
*       2.Map以键值对的方式进行存储；key、value均为引用类型，存储对象内存地址
*       3.Map接口中常用方法
*           V put(K key, V value)
*           void clear()
*           V get(Object key)
*           boolean containsKey(Object key);
*           boolean containsValue(Object value);
*           Set<K> keySet();//获取map集合所有的key
*           Collection<V> values();//获取所有的value集合
*
*           boolean isEmpty();
*           V remove(Object key);
*           int size();
*           Set<Map.Entry<K, V>> entrySet();//将map集合转化为set集合
*
* */

public class MapSamp {
    public static void main(String[] args) {
//        Samp1();
        Samp3();
    }

    public static void Samp1(){
        Map<Integer,String> map = new HashMap<>();
        map.put(2,"sam");
        map.put(3,"levi");
        map.put(4,"jack");
        map.put(5,"john");

        //map.keySet()获取包含所有key的set
        Set<Integer> set = map.keySet();
/*
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            Integer key = iterator.next();
            String val = map.get(key);
            System.out.println(key + " = " + val);
        }
*/
/*

        for (Integer key : set){
            System.out.println(key + " = " + map.get(key));
        }
*/

        //map.entrySet()获取包含Entry的set集合
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            Integer key = entry.getKey();
            String val = entry.getValue();
            System.out.println(key + " = " + val);
        }

    }

    /*
    *   放在hashMap集合的key部分的元素，以及放在hashSet集合中的元素类型，需要同时重写equals和hashCode方法
    *       多态:
    *           1.基于重写机制
    *           2.函数成员的具体行为由对象决定
    * */
    public static void Samp2(){

    }

    /*
    *   hashTable的key和value均不能为null
    *   hashMap的key、value中可以存储null值
    *   properties是HashTable的子类,只支持key、value均为String类型
    * */
    public static void Samp3(){
        Properties properties = new Properties();
        properties.setProperty("url","jdbc:mysql://localhost:3306");
        properties.setProperty("driver","jdbc");
        properties.setProperty("user","xxx");
        properties.setProperty("pwd","123");

        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        String user = properties.getProperty("user");
        String pwd = properties.getProperty("pwd");

        System.out.println(url);
        System.out.println(driver);
        System.out.println(user);
        System.out.println(pwd);
    }
}
