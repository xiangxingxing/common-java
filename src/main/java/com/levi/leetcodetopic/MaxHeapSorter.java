package com.levi.leetcodetopic;

import java.util.Arrays;

public class MaxHeapSorter {
    public static void main(String[] args) {
        Integer[] integers = {32, 41, 12, 3, 66, 8, 48, 90, 41};
        Integer[] expected  = {3, 8, 12, 32, 41, 41, 48, 66, 90};

        heapSort(integers);
        System.out.println(Arrays.equals(integers, expected));//true
    }

    /**
     *       1.构造大顶堆(从最后一个分支节点往前调整) buildMaxHeap
     *       2.lastIndex-- 循环交换收尾元素，堆调整首元素
     * */
    public static <T extends Comparable<T>> void heapSort(T[] array){

        var lastIndex = array.length - 1;
        var lastNodeWithChild = array.length / 2 - 1;
        for (int i = lastNodeWithChild; i >= 0;i--){
            AdjustHeap(array, i, lastIndex);
        }

        while (lastIndex >= 0){
            swapTwo(array, 0, lastIndex);
            lastIndex--;
            AdjustHeap(array, 0, lastIndex);
        }

    }

    //大根堆调整
    public static <T extends Comparable<T>> void AdjustHeap(T[] array, int node, int lastIndex) {
        var lChild = node * 2 + 1;
        var rChild = lChild + 1;
        var largest = node;

        if (lChild <= lastIndex && array[lChild].compareTo(array[largest]) > 0) {
            largest = lChild;
        }

        if (rChild <= lastIndex && array[rChild].compareTo(array[largest]) > 0) {
            largest = rChild;
        }

        if (node != largest) {
            swapTwo(array, node, largest);
            AdjustHeap(array, largest, lastIndex);
        }


    }

    private static <T> void swapTwo(T[] array, int index1, int index2) {
        if (array.length < 2) {
            return;
        }

        var temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
