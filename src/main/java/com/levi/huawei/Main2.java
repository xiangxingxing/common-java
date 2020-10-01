package com.levi.huawei;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        rotateSort();
    }

    private static void rotateSort(){
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();//行
        int n = in.nextInt();//列
        PriorityQueue<Integer> queue = new PriorityQueue<>(m * n);
        for(int i = 0; i < m * n; i++){
            queue.offer(in.nextInt());
        }
        int[][] matrix = new int[m][n];
        internalRotateSort(queue, matrix, 0, m - 1, 0, n - 1);

        printMatrix(matrix);
    }

    private static void internalRotateSort(Queue<Integer> queue, int[][] matrix, int x1, int x2, int y1, int y2){
        if(queue.size() == 0){
            return;
        }
        if(x1 > x2 || y1 > y2){
            return;
        }

        for(int j = y1; j <= y2; j++){
            Integer num = queue.poll();
            matrix[x1][j] = num;
        }
        if(queue.size() == 0){
            return;
        }

        for(int i = x1 + 1; i <= x2; i++){
            Integer num = queue.poll();
            matrix[i][y2] = num;
        }
        if(queue.size() == 0){
            return;
        }

        for(int k = y2 - 1; k >= y1; k--){
            Integer num = queue.poll();
            matrix[x2][k] = num;
        }
        if(queue.size() == 0){
            return;
        }

        for(int t = x2 - 1; t > x1; t--){
            Integer num = queue.poll();
            matrix[t][y1] = num;
        }
        if(queue.size() == 0){
            return;
        }

        internalRotateSort(queue, matrix, x1 + 1, x2 - 1, y1 + 1, y2 - 1);
    }

    private static void printMatrix(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
