package com.levi.leetcodetopic;

import java.util.Arrays;

public class SelectionSorter {
    public static void main(String[] args) {
        Integer[] array = {6, 23, 40, 12, 9, 20, 23, 9, 2};
        Integer[] expected = {2, 6, 9, 9, 12, 20, 23, 23, 40};

        selectSort(array);
        System.out.println(Arrays.equals(array, expected));
    }

    public static <T extends Comparable<T>> void selectSort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[min]) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                swap(array, i, min);
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
