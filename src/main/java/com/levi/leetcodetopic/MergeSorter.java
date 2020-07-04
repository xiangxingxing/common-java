package com.levi.leetcodetopic;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeSorter {

    public static void main(String[] args) {
        Integer[] integers = {32, 41, 12, 3, 66, 8, 48, 90, 41};
        Integer[] expected = {3, 8, 12, 32, 41, 41, 48, 66, 90};

        mergeSort(integers);// 3 8 12 32 41 41 48 66 90
        System.out.println(Arrays.equals(integers, expected));//true

    }

    public static <T extends Comparable<T>> T[] mergeSort(T[] array) {
        if (array.length < 2) {
            return array;
        }
        //分治
        int mid = array.length >>> 1;
        T[] left = Arrays.copyOfRange(array, 0, mid);
        T[] right = Arrays.copyOfRange(array, mid, array.length);

        left = mergeSort(left);
        right = mergeSort(right);

        ArrayList<T> list = internalMergeSort(left, right);
        return list.toArray(array);
    }

    private static <T extends Comparable<T>> ArrayList<T> internalMergeSort(T[] a1, T[] a2) {
        int left = 0;
        int right = 0;
        var res = new ArrayList<T>();
        while (left < a1.length && right < a2.length) {
            if (a1[left].compareTo(a2[right]) < 0) {
                res.add(a1[left++]);
            } else {
                res.add(a2[right++]);
            }
        }

        while (left < a1.length) {
            res.add(a1[left++]);
        }

        while (right < a2.length) {
            res.add(a2[right++]);
        }

        return res;
    }

}
