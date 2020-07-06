package com.levi.leetcodetopic.recursive;

public class MyFibonaci {
    public static void main(String[] args) {
        System.out.println(climbStairs(3));
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


}
