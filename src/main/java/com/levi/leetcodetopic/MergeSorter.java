package com.levi.leetcodetopic;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeSorter {

    public static void main(String[] args) {
        Integer[] integers = {32, 41, 12, 3, 66, 8, 48, 90, 41};
        Integer[] expected  = {3, 8, 12, 32, 41, 41, 48, 66, 90};

        mergeSort(integers);// 3 8 12 32 41 41 48 66 90
        System.out.println(Arrays.equals(integers, expected));//true

    }

    public static <T extends Comparable<T>> void mergeSort(T[] collection) {
        internalMergeSort(collection);
    }

    public static <T extends Comparable<T>> T[] internalMergeSort(T[] collection) {
        if (collection.length < 2) {
            return collection;
        }

        int mid = collection.length >>> 1;
        T[] left = Arrays.copyOfRange(collection, 0, mid);
        T[] right = Arrays.copyOfRange(collection, mid, collection.length);

        left = internalMergeSort(left);
        right = internalMergeSort(right);

        ArrayList<T> list = internalMerge(left, right);
        return list.toArray(collection);

    }

    public static <T extends Comparable<T>> ArrayList<T> internalMerge(T[] leftCollection, T[] rightCollection) {
        int left = 0;
        int right = 0;
        int index = 0;
        int size = leftCollection.length + rightCollection.length;
        var arrayList = new ArrayList<T>(size);

        while (left < leftCollection.length && right < rightCollection.length) {
            if (leftCollection[left].compareTo(rightCollection[right]) < 0) {
                /*
                        ‼️不能用set‼️
                        ‼️要使用add方法为数组添加新元素，而不是set方法，set方法只是用来替换数组中已经加入的元素‼️
                * */
                //arrayList.set(index++, leftCollection[left++]);
                arrayList.add(index++, leftCollection[left++]);
            } else {
                arrayList.add(index++, rightCollection[right++]);
            }
        }

        while (left < leftCollection.length) {
            arrayList.add(index++, leftCollection[left++]);
        }

        while (right < rightCollection.length) {
            arrayList.add(index++, rightCollection[right++]);
        }

        return arrayList;
    }

}