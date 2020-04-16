package com.levi.algorithms.search;

public class BinarySearcher {
    public static <T extends Comparable<T>> int BinarySearch(T[] array, T target) {
        int low = 0;
        int high = array.length - 1;
        int indexOfKey = BinarySearch(array, low, high, target);
        return indexOfKey;
    }

    private static <T extends Comparable<T>> int BinarySearch(T[] array, int low, int high, T target) {
        //‼️必须是等于号 <= ‼️
        while (low <= high) {
            int mid = (low + high) >>> 1;
            T midVal = array[mid];
            if (midVal.compareTo(target) > 0) {
                high = mid - 1;
            } else if (midVal.compareTo(target) < 0) {
                low = mid + 1;
            } else {
                return mid;//key found
            }
        }

        return -(low + 1);//key not found
    }
}
