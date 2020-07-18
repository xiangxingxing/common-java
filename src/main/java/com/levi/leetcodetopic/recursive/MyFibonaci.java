package com.levi.leetcodetopic.recursive;

import java.util.Arrays;
import java.util.Scanner;

public class MyFibonaci {
    public static void main(String[] args) {
        //System.out.println(climbStairs(3));
        //steps();
        //System.out.println(coinChange(27, new int[]{2, 5, 7}));
        System.out.println(stepNoRecursive(3, 4));
    }

    /**
     * LeetCode509. 斐波那契数
     */
    public int fib(int N) {
        /* 效率最低
        if (N < 2){
            return N;
        }

        return fib(N - 1) + fib(N - 2);
        */

        /* 非递归
        if (N < 2){
            return N;
        }

        int n1 = 0;
        int n2 = 1;
        int res = 0;
        for (int i = 2; i <= N; i++){
            res = n1 + n2;
            n1 = n2;
            n2 = res;
        }

        return res;
        */
        int[] nums = new int[N + 1];//缓存
        return internalFib(N, nums);

    }

    public int internalFib(int N, int[] tb) {
        if (tb[N] != 0) {
            return tb[N];
        }

        if (N < 2) {
            tb[N] = N;
            return N;
        }

        tb[N] = internalFib(N - 1, tb) + internalFib(N - 2, tb);

        return tb[N];
    }

    /**
     * LeetCode70. 爬楼梯
     * 1.递归
     * 通过构造n+1大小的数组,用于存储F(n),避免重复计算
     */
    //O(n)
    public static int climbStairs(int n) {
        int[] array = new int[n + 1];
        return climbStairsMethod(n, array);//作为参数传入,确保同一实例

    }

    /**
     * @param n     第n层楼梯
     * @param array array[n]存放爬n层楼梯所需步数
     */
    private static int climbStairsMethod(int n, int[] array) {
        if (array[n] > 0) {
            return array[n];//计算过了,则直接返回,避免重复计算
        }

        if (n < 3) {
            array[n] = n;
            return n;
        }

        array[n] = climbStairsMethod(n - 1, array) + climbStairsMethod(n - 2, array);
        return array[n];
    }

    /**
     * LeetCode70. 爬楼梯
     * 2.非递归
     */
    public static int climbStairs2(int n) {
        if (n < 3) {
            return n;
        }

        int p = 1;
        int q = 2;
        int res = 0;
        for (int i = 3; i <= n; i++) {
            res = p + q;
            p = q;
            q = res;
        }

        return res;
    }

    /**
     * 有一个X*Y的网格，小团要在此网格上从左上角到右下角，只能走格点且只能向右或向下走。
     * 请设计一个算法，计算小团有多少种走法。给定两个正整数int x,int y，请返回小团的走法数目。
     * 输入 3 2 【表示的是四行三列】
     * 输出 10
     */
    public static void steps() {
        try (var sc = new Scanner(System.in)) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            System.out.println(step(m, n));
        }
    }

    public static int step(int x, int y) {
        if (x == 0 || y == 0) {
            return 1;
        }
        return step(x - 1, y) + step(x, y - 1);
    }

    public static int stepNoRecursive(int x, int y) {
        int[][] mark = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (i == 0 || j == 0) {
                    mark[i][j] = 1;
                } else {
                    mark[i][j] = mark[i - 1][j] + mark[i][j - 1];
                }
            }
        }
        System.out.println(Arrays.deepToString(mark));


        return mark[x - 1][y - 1];
    }

    private static int coinChange(int amount, int[] coins) {
        int[] mark = new int[amount + 1];//mark[i]表示总金额i所需最少的硬币个数
        Arrays.fill(mark, amount + 1);
        mark[0] = 0;

        for (int i = 1; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    int minCount = mark[i - coins[j]] + 1;
                    mark[i] = Math.min(mark[i], minCount);
                }
            }
        }

        return mark[amount] == amount + 1 ? -1 : mark[amount];
    }

    public boolean canJump(int[] A) {
        // write your code here
        int n = A.length;
        boolean mark[] = new boolean[n];//mark[i] 表示从原点是否可达i
        Arrays.fill(mark, false);
        mark[0] = true;
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){
                if (mark[j] && (A[j] + j >= i)){
                    mark[i] = true;
                    break;
                }
            }
        }

        return mark[n - 1];
    }

}
