package com.levi.algorithms.sorters;

public class QuickSorter1 {
    public static <T extends Comparable<T>> void QuickSort(T[] array) {
        int left = 0;
        int right = array.length - 1;
        QuickSort(array, left, right);
    }

    public static <T extends Comparable<T>> void QuickSort(T[] array, int left, int right) {
        if (left < right) {
            int pivot = partition(array, left, right);
            QuickSort(array, left, pivot - 1);
            QuickSort(array, pivot + 1, right);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        T temp = array[left];

        while (left < right) {
            //array[right].compareTo(temp) >= 0中必须有等于号‼️
            while (left < right && array[right].compareTo(temp) >= 0) {
                right--;
            }
            array[left] = array[right];
            while (left < right && array[left].compareTo(temp) <= 0) {
                left++;
            }
            array[right] = array[left];
        }

        array[left] = temp;
        return left;
    }
}
