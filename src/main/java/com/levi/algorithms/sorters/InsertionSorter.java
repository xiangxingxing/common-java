package com.levi.algorithms.sorters;

public class InsertionSorter {
    public static <T extends Comparable<T>> void InsertionSort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T temp = array[i];
            int j = i - 1;
            while (j >= 0 && temp.compareTo(array[j]) < 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }
}
