package com.levi.leetcodetopic.recursive;

public class MyFibonaci {
    public static void main(String[] args) {
        System.out.println(climbStairs(3));
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
     * @param n 第n层楼梯
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
