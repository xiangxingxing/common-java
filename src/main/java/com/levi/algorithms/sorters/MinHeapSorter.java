package com.levi.algorithms.sorters;

import com.levi.common.Utilities;

public class MinHeapSorter {
    public static <T extends Comparable<T>> void MinHeapSort(T[] array) {
        //构建小根堆
        BuildMinHeap(array);

        //将首元素和末尾元素交换，之后堆调整第一个位置
        int lastIndex = array.length - 1;
        while (lastIndex >= 0) {
            Utilities.swap(array, 0, lastIndex);
            lastIndex--;
            //小根堆调整
            AdjustMinHeap(array, 0, lastIndex);
        }
    }

    private static <T extends Comparable<T>> void BuildMinHeap(T[] array) {
        int lastIndex = array.length - 1;
        int lastNodeWithChild = lastIndex >>> 1;
        for (int i = lastNodeWithChild; i >= 0; i--) {
            AdjustMinHeap(array, i, lastIndex);
        }
    }

    private static <T extends Comparable<T>> void AdjustMinHeap(T[] array, int node, int lastIndex) {
        int leftChild = node * 2 + 1;
        int rightChild = leftChild + 1;
        int minimum = node;
        if (leftChild <= lastIndex && array[leftChild].compareTo(array[minimum]) < 0) {
            minimum = leftChild;
        }
        if (rightChild <= lastIndex && array[rightChild].compareTo(array[minimum]) < 0) {
            minimum = rightChild;
        }

        if (minimum != node) {
            Utilities.swap(array, minimum, node);
            AdjustMinHeap(array, minimum, lastIndex);
        }
    }

}
