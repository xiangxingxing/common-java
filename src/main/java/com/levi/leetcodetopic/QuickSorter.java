package com.levi.leetcodetopic;

import java.util.Arrays;

public class QuickSorter {
    public static void main(String[] args) {
        Integer[] integers = {32, 41, 12, 3, 66, 8, 48, 90, 41};
        Integer[] expected  = {3, 8, 12, 32, 41, 41, 48, 66, 90};

        quickSort(integers);// 3 8 12 32 41 41 48 66 90
        System.out.println(Arrays.equals(integers, expected));

    }

    public static <T extends Comparable<T>> void quickSort(T[] array){
        int left = 0;
        int right = array.length - 1;
        partition(array,left, right);
    }

    private static <T extends Comparable<T>> void partition(T[] array, int start, int end){
        int low = start;
        int high = end;

        var temp = array[start];
        while (start < end){
            while (start < end && array[end].compareTo(temp) >= 0){
                end--;
            }
            array[start] = array[end];

            while (start < end && array[start].compareTo(temp) <= 0){
                start++;
            }
            array[end] = array[start];
        }

        array[start] = temp;

        if(low < high){
            partition(array, low, start - 1);
            partition(array, start + 1, high);
        }
    }
}
