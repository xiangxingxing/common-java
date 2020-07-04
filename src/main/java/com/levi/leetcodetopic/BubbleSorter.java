package com.levi.leetcodetopic;

import java.util.Arrays;

public class BubbleSorter {
    public static void main(String[] args) {
        Integer[] array = {6, 23, 40, 12, 9, 20, 23, 9, 2};
        Integer[] expected = {2, 6, 9, 9, 12, 20, 23, 23, 40};

        bubbleSort(array);
        System.out.println(Arrays.equals(array, expected));
    }

    /*
    *   O(x) = O(n²)
    * */
    public static <T extends Comparable<T>> void bubbleSort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    private static <T extends Comparable<T>> void swap(T[] array, int index1, int index2) {
        if (array.length < 2) {
            return;
        }

        var temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
