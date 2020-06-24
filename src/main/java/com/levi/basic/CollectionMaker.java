package com.levi.basic;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionMaker {
    public static void main(String[] args) {
//        int[] h = {1, 2, 3, 3, 3, 3, 6, 6, 6,};
//        int newCapacity = 12;
//        int i[] = Arrays.copyOf(h, newCapacity);
//        for (int item : i){
//            System.out.println(item);
//        }
        //ArrayCopy();
        arrayAsList();
    }

    /*
    * 将数组转换为ArrayList
    * 1.原数组用包装类型
    * 2.简便: new ArrayList<>(Arrays.asList(myArray)）
    * 3.使用流: Arrays.stream(myArray).collect(Collectors.toList())
    * 4.使用 Guava
    * */

    public static void arrayAsList(){
        Integer[] myArray = { 1, 2, 3 };
        List<Integer> list = Arrays.asList(myArray);
        List<Integer> integers = new ArrayList<>(list);
        System.out.println(list.getClass());//class java.util.Arrays$ArrayList


        List myList = Arrays.stream(myArray).collect(Collectors.toList());
        myList.forEach(System.out::println);

    }

    /*
     * 将ArrayList转换为数组
     * 1.new String[0]就是起一个模板的作用，指定了返回数组的类型，0是为了节省空间，因为它只是为了说明返回的类型
     * */
    //正确的将数组转换为ArrayList --> 使用new ArrayList<>包一层
    public static void ConvertArrayList2List(){
        //List<String> list = new ArrayList<>(Arrays.asList("apple","banana","strawberry"));
        List<String> list = new ArrayList<>(List.of("apple","banana","strawberry"));
        //String[] res = list.toArray(new String[list.size()]); //将list转换为String数组
        list.add("watermelon");
        //list.remove(0);
        list.forEach(System.out :: println);
    }

    //移除数组元素不能用foreach，应利用集合的迭代器
    public static void RemoveCollectionItem(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        //wrong
//        for (String item : list){
//            if("1".equals(item)){
//                list.remove(item);
//            }
//        }

        //right
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String item = iterator.next();
            if (item == "2"){
                iterator.remove();
            }
        }

        for (String item : list){
            System.out.println(item);
        }
    }

    private static void ArrayCopy() {
        // copyOf 方法实现数组复制,h为数组，6为复制的长度
        int[] h = {1, 2, 3, 3, 3, 3, 6, 6, 6};
        int i[] = Arrays.copyOf(h, 6);
        System.out.println("Arrays.copyOf(h, 6);：");
        // 输出结果：123333
        for (int j : i) {
            System.out.print(j);
        }
        int[] b = new int[]{1,2,4};
        // 换行
        System.out.println();
        // copyOfRange将指定数组的指定范围复制到新数组中
        int j[] = Arrays.copyOfRange(h, 6, 9);
        System.out.println("Arrays.copyOfRange(h, 6, 9)：");
        // 输出结果66600(h数组只有9个元素这里是从索引6到索引11复制所以不足的就为0)
        for (int j2 : j) {
            System.out.print(j2);
        }
        // 换行
        System.out.println();
    }

    private static void ArrayBinarySearch() {
        // *************查找 binarySearch()****************
        char[] e = {'a', 'f', 'b', 'c', 'e', 'A', 'C', 'B'};
        // 排序后再进行二分查找，否则找不到
        //Arrays.sort(e);
        //System.out.println("Arrays.sort(e)" + Arrays.toString(e));
        System.out.println("Arrays.binarySearch(e, 'c')：");
        int s = Arrays.binarySearch(e, 'f');
        System.out.println("字符c在数组的位置：" + s);
    }
}
