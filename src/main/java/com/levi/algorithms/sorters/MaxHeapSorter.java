package com.levi.algorithms.sorters;

import com.levi.common.Utilities;

public class MaxHeapSorter {

    public static <T extends Comparable<T>> void MaxHeapSort(T[] array) {
        //第一步：构建成大根堆:从最后一个分支结点向前遍历进行堆调整
        BuildMaxHeap(array);
        int lastIndex = array.length - 1;
        //第二部：从大根堆的最后一个元素开始，与第一个最大的元素进行交换，之后再进行第一个位置的堆调整
        while (lastIndex >= 0) {
            Utilities.swap(array, 0, lastIndex);
            lastIndex--;
            AdjustMaxHeap(array, 0, lastIndex);
        }
    }

    private static <T extends Comparable<T>> void BuildMaxHeap(T[] array) {
        int lastIndex = array.length - 1;
        int lastNodeWithChild = (array.length >>> 1) - 1;
        for (int i = lastNodeWithChild; i >= 0; i--) {
            AdjustMaxHeap(array, i, lastIndex);
        }
    }

    private static <T extends Comparable<T>> void AdjustMaxHeap(T[] array, int node, int lastIndex) {
        int leftChild = node * 2 + 1;
        int rightChild = leftChild + 1;
        int largest = node;
        if (leftChild <= lastIndex && array[leftChild].compareTo(array[largest]) > 0) {
            largest = leftChild;
        }
        if (rightChild <= lastIndex && array[rightChild].compareTo(array[largest]) > 0) {
            largest = rightChild;
        }

        if (largest != node) {
            Utilities.swap(array, largest, node);
            AdjustMaxHeap(array, largest, lastIndex);
        }
    }


}
