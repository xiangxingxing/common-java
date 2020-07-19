package com.levi.dataStructures.linkcode;

/**
 * 动态规划
 *      1.确定状态【最后一步 --> 子问题】
 *      2.转移方程
 *      3.初始条件、边界
 *      4.计算顺序
 *
 * 特点：
 *      1.计数
 *      2.求最大/最小值
 *      3.求存在性
 * */
public class DynamicPlan {
    /**
     * linkCode114.不同的路径 【坐标型coordinator type】
     * 有一个机器人的位于一个 m × n 个网格左上角。
     * 机器人每一时刻只能向下或者向右移动一步。机器人试图达到网格的右下角。
     * 问有多少条不同的路径？
     *
     * 1.确定状态
     *          走倒数第二步分为(m - 2, n - 1)、(m - 1, n - 2)
     *          子问题
     * 2.转移方程
     *          设f[i][j]表示走到(i, j)处的所有可能路径数
     * 3.初始条件和边界
     *          f[0][0] = 1;
     *          i = 0 || j = 0 -> f[i][j] = 1
     * 4.计算顺序
     *      遍历每行
     * */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (i == 0 || j == 0){
                    dp[i][j] = 1;
                    continue;
                }

                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // write your code here
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;// ‼️计算完毕,就continue‼️
                }

                if (i == 0 && j == 0) {
                    dp[0][0] = 1;
                    continue;
                }
                if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                }
                if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                }
                //dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * linkCode397.最长上升连续子序列 【坐标型coordinator type】
     * 给定一个整数数组（下标从 0 到 n-1， n 表示整个数组的规模），请找出该数组中的最长上升连续子序列。
     * （最长上升连续子序列可以定义为从右到左或从左到右的序列。）
     * 输入：[5, 1, 2, 3, 4]
     * 输出：4
     * 1.确定状态
     *          走倒数第二步 f[i] = f[i - 1] + 1 在a[i] > a[i - 1] && i > 0前提下
     *          原问题 求以array[i]结尾的数组中的最长上升连续子序列
     *          子问题 求以array[i - 1]结尾的数组中的最长上升连续子序列
     * 2.转移方程
     *          设f[i]表示能到该位置的最长连续子序列的长度
     * 3.初始条件和边界
     *          f[0] = 1;
     *
     * 4.计算顺序
     *      从左往右
     * */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        // write your code here
        int i = 0;
        int j = A.length - 1;
        int res = lic(A);
        while (i < j){
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
            i++;
            j--;
        }
        int reversed = lic(A);
        return Math.max(res, reversed);
    }

    private int lic(int[] array) {
        int[] dp = new int[array.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            if (i > 0  && array[i] > array[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
        }

        int res = 0;
        for (int m : dp){
            res = Math.max(res, m);
        }

        return res;
    }

    /**
     *  linkCode512.有一个消息包含A-Z通过以下规则编码,现在给你一个加密过后的消息，问有几种解码的方式
     *  'A' -> 1
     *  'B' -> 2
     *  ...
     *  'Z' -> 26
     *
     * 1.确定状态
     *
     *          子问题
     * 2.转移方程
     *          设f[i]表示到i的字符的解码方式数
     *          f[i] = f[i - 1] && s[i - 1] ∈ (1,9)  +  f[i - 2] && s[i-2] * 10 + s[i-2] ∈ (10, 26)
     * 3.初始条件和边界
     *          f[0] = 0;
     *          i = 0 || j = 0 -> f[i][j] = 1
     * 4.计算顺序
     *      遍历每行
     * */
    public int numDecodings(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        if (n == 0){
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i < n + 1; i++){
            dp[i] = 0;
            if (chars[i - 1] >= '1' && chars[i - 1] <= '9'){
                dp[i] += dp[i - 1];
            }

            if (i > 1){
                int j = (chars[i - 2] - '0') * 10 + (chars[i - 1] - '0');
                if (j >= 10 && j <= 26){
                    dp[i] += dp[i - 2];
                }
            }
        }

        return dp[n];

    }

    /**
     * linkCode515.房屋染色 [序列型动态规划：序列+状态]
     * 这里有n个房子在一列直线上，现在我们需要给房屋染色，分别有红色蓝色和绿色。每个房屋染不同的颜色费用也不同，
     * 你需要设计一种染色方案使得相邻的房屋颜色不同，并且费用最小，返回最小的费用。
     *
     * 费用通过一个nx3 的矩阵给出，比如cost[0][0]表示房屋0染红色的费用，cost[1][2]表示房屋1染绿色的费用。
     * 输入: [[14,2,11],[11,14,5],[14,3,10]]
     * 输出: 10
     * 解释: 第一个屋子染蓝色，第二个染绿色，第三个染蓝色，最小花费：2 + 5 + 3 = 10.
     * */
    public static int minCost(int[][] costs) {
        if (costs == null || costs.length < 1){
            return 0;
        }

        int m = costs.length;//m栋房子
        int n = costs[0].length;//n个花色对应的各自费用

        int[][] dp = new int[m + 1][n];//dp[i][j]表示前i栋最低的费用
        //dp[0][n]表示前0栋
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[0][2] = 0;
        for (int i = 1; i <= m; i++){
            for (int j = 0; j < 3; j++){
                dp[i][j] = Integer.MAX_VALUE;
                //考虑i-1的状况
                for (int k = 0; k < 3; k++){
                    if (j != k){
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + costs[i - 1][j]);
                    }
                }
            }
        }

        return Math.min(dp[m][0], Math.min(dp[m][1], dp[m][2]));
    }

}
