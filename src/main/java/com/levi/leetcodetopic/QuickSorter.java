package com.levi.leetcodetopic;

public class QuickSorter {
    public static void main(String[] args) {
        Integer[] integers = {32, 41, 12, 3, 66, 8, 48, 90, 41};
        quickSort(integers);// 3 8 12 32 41 41 48 66 90

        for (Integer i : integers) {
            System.out.print(i + " ");
        }

    }

    public static <T extends Comparable<T>> void quickSort(T[] collection) {
        int left = 0;
        int right = collection.length - 1;
        quickSort(collection, left, right);

    }

    private static <T extends Comparable<T>> void quickSort(T[] collection, int left, int right) {
        if (left < right) {
            int pivot = partition(collection, left, right);
            quickSort(collection, left, pivot - 1);
            quickSort(collection, pivot + 1, right);
        }

    }

    private static <T extends Comparable<T>> int partition(T[] collection, int left, int right) {
        T value = collection[left];

        while (left < right) {

            while (left < right && collection[right].compareTo(value) >= 0) {
                right--;
            }

            collection[left] = collection[right];

            while (left < right && collection[left].compareTo(value) <= 0) {
                left++;
            }

            collection[right] = collection[left];
        }
        collection[left] = value;

        return left;
    }

    private static <T extends Comparable<T>> void swap(T[] collection, int index1, int index2) {
        if (collection.length < 2) {
            return;
        }

        var temp = collection[index1];
        collection[index1] = collection[index2];
        collection[index2] = temp;
    }
}
