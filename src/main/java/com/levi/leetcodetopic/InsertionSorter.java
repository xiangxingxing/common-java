package com.levi.leetcodetopic;

import java.util.Arrays;

public class InsertionSorter {
    public static void main(String[] args) {
        Integer[] array = {6, 23, 40, 12, 9, 20, 23, 9, 2};
        Integer[] expected = {2, 6, 9, 9, 12, 20, 23, 23, 40};

        insertionSort(array);
        //insertionSort2(array);
        System.out.println(Arrays.equals(array, expected));
    }

    /*
     * 推荐 for + while 循环
     * */
    public static <T extends Comparable<T>> void insertionSort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            var temp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j].compareTo(temp) > 0) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = temp;
        }
    }

    public static <T extends Comparable<T>> void insertionSort2(T[] array) {
        for (int i = 1; i < array.length; i++) {
            var temp = array[i];
            for (int j = i - 1; j >= 0; j--) {
                if (array[j].compareTo(temp) > 0) {
                    array[j + 1] = array[j];
                    array[j] = temp;
                } else {
                    array[j + 1] = temp;
                    break;
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
