package com.levi.leetcodetopic;

import java.util.Arrays;

public class MinHeapSorter {
    public static void main(String[] args) {
        Integer[] integers = {32, 41, 12, 3, 66, 8, 48, 90, 41};
        Integer[] expected2 = {90, 66, 48, 41, 41, 32, 12, 8, 3};

        minHeapSort(integers);
        System.out.println(Arrays.equals(integers, expected2));

    }

    /*
     * 1.从lastNodeWithChild往前遍历，构建小根堆
     * 2.交换收尾元素后，对首元素堆调整
     * */
    public static <T extends Comparable<T>> void minHeapSort(T[] array) {
        /**  构建小根堆 **/
        int lastIndex = array.length - 1;
        var lastNodeWithChild = (array.length >>> 1) - 1;
        for (int i = lastNodeWithChild; i >= 0; i--) {
            heapify(array, i, lastIndex);
        }

        /**  交换首尾 **/
        while (lastIndex >= 0) {
            swap(array, 0, lastIndex);
            lastIndex--;
            heapify(array, 0, lastIndex);
        }
    }

    private static <T extends Comparable<T>> void heapify(T[] array, int node, int lastIndex) {
        var leftChild = node * 2 + 1;
        var rightChild = leftChild + 1;
        var smallest = node;
        if (leftChild <= lastIndex && array[leftChild].compareTo(array[smallest]) < 0) {
            smallest = leftChild;
        }

        if (rightChild <= lastIndex && array[rightChild].compareTo(array[smallest]) < 0) {
            smallest = rightChild;
        }

        if (smallest != node) {
            swap(array, smallest, node);
            heapify(array, smallest, lastIndex);
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
