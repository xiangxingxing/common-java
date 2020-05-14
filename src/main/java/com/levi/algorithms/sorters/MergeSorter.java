package com.levi.algorithms.sorters;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeSorter {
    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        internalMergeSort(array);
    }

    private static <T extends Comparable<T>> T[] internalMergeSort(T[] array) {
        if (array.length < 2){
            return array;
        }

        int midIndex = array.length >> 1;
        T[] leftCollection = Arrays.copyOfRange(array,0, midIndex);
        T[] rightCollection = Arrays.copyOfRange(array, midIndex, array.length);

        leftCollection = internalMergeSort(leftCollection);
        rightCollection = internalMergeSort(rightCollection);

        ArrayList<T> merge = internalMerge(leftCollection, rightCollection);
        return merge.toArray(array);
    }

    //两路归并算法，两个排好序的子序列合并为一个子序列
    private static <T extends Comparable<T>> ArrayList<T> internalMerge(T[] leftCollection, T[] rightCollection) {
        ArrayList<T> res = new ArrayList<>();
        int left = 0;//左数组索引
        int right = 0;//右数组索引
        while (left < leftCollection.length && right < rightCollection.length){
            if (leftCollection[left].compareTo(rightCollection[right]) < 0){
                res.add(leftCollection[left++]);
            }
            else {
                res.add(rightCollection[right++]);
            }
        }
        while (left < leftCollection.length){
            res.add(leftCollection[left++]);
        }

        while (right < rightCollection.length){
            res.add(rightCollection[right++]);
        }

        return res;
    }

}
