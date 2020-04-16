package com.levi.algorithms.sorters;

import com.levi.common.Utilities;

import java.util.Comparator;

public class QuickSorter2 {

    public static <T extends Comparable> void QuickSort(T[] array) {
        Comparator<T> comparator = Comparable::compareTo;
        int left = 0;
        int right = array.length - 1;
        QuickSortAscending(array, left, right, comparator);

    }

    public static <T> void QuickSortAscending(T[] array, int left, int right, Comparator<T> comparator) {
        int i = left;
        int j = right;
        T pivot = array[(i + j) >> 1];

        while (i <= j) {
            while (comparator.compare(array[i], pivot) < 0) {
                i++;
            }
            while (comparator.compare(array[j], pivot) > 0) {
                j--;
            }

            if (i <= j) {
                Utilities.swap(array, i, j);
                i++;
                j--;
            }
        }

        if (i < right) {
            QuickSortAscending(array, i, right, comparator);
        }

        if (j > left) {
            QuickSortAscending(array, left, j, comparator);
        }

    }
}
