package com.levi.common;

public class Utilities {

    //一个泛型的获取数组最小值的函数.并且这个方法只能接受Number的子类并且实现了Comparable接口
    public static <T extends Number & Comparable<? super T>> T minOf(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        T minVal = array[0];
        for (int i = 1; i < array.length; i++) {
            if (minVal.compareTo(array[i]) > 0) {
                minVal = array[i];
            }
        }

        return minVal;
    }
}
