package com.levi.algorithms.sorters;

import java.util.Arrays;

public class MergeSorter {
//    public static <T extends Comparable<T>> void mergeSort(T[] array, T[] temp) {
//        mergeSort(array, 0, array.length - 1, temp);
//    }
//
//    private static <T extends Comparable<T>> void mergeSort(T[] a, int start, int end, T[] temp) {
//        if (start < end) {//当子序列中只有一个元素时结束递归
//            int mid = (start + end) / 2;//划分子序列
//            mergeSort(a, start, mid, temp);//对左侧子序列进行递归排序
//            mergeSort(a, mid + 1, end, temp);//对右侧子序列进行递归排序
//            internalMerge(a, start, mid, end, temp);//合并
//        }
//    }
//
//    //两路归并算法，两个排好序的子序列合并为一个子序列
//    private static <T extends Comparable<T>> void internalMerge(T[] array, int left, int mid, int right, T[] temp) {
//        //T[] temp = Arrays.copyOfRange(array, left, right + 1);//辅助数组
//        int p1 = left;
//        int p2 = mid + 1;
//        int k = left;//p1、p2是检测指针，k是存放指针
//
//        while (p1 <= mid && p2 <= right) {
//            if (temp[p1].compareTo(temp[p2]) <= 0)
//                array[k++] = temp[p1++];
//            else
//                array[k++] = temp[p2++];
//        }
//
//        while (p1 <= mid) array[p1++] = temp[k++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
//        while (p2 <= right) array[p2++] = temp[k++];//同上
//    }

}
