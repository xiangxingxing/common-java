package com.levi.dataStructures.lintcode;

import java.util.Arrays;
import java.util.stream.Stream;

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
     * linkCode110.最小路径和 【坐标型coordinator type】
     * 给定一个只含非负整数的m*n网格，找到一条从左上角到右下角的可以使数字和最小的路径。
     * 输入:  [[1,3,1],[1,5,1],[4,2,1]]
     * 	输出: 7
     *
     * 	样例解释：
     * 	路线为： 1 -> 3 -> 1 -> 1 -> 1。
     *
     * 1.确定状态
     *          走倒数第二步 前提下
     *          原问题 到右下角f[m - 1][n - 1]数字和最小的路径
     *          子问题 f[m - 2][n - 1] or f[m - 1][n - 2]数字和最小
     * 2.转移方程
     *          设f[i][j] 表示从左上角f[0][0]到f[m - 1][m - 1]的数字和最小路径
     * 3.初始条件和边界
     *          f[0][0] = grid[0][0];
     *
     * 4.计算顺序
     *      从左往右,从上至下
     * */
    public int minPathSum(int[][] grid) {
        // write your code here
        int m = grid.length;
        if (m == 0){
            return 0;
        }
        int n = grid[0].length;
        if(n == 0){
            return 0;
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (i == 0 && j == 0){
                    dp[i][j] = grid[i][j];
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;//求最小路径 --> 设初值为maxValue
                if(i > 0){
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j]);
                }
                if (j > 0){
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i][j]);
                }

                dp[i][j] += grid[i][j];

            }
        }
        return dp[m - 1][n - 1];
    }

    //滚动数组
    public int minPathSum2(int[][] grid) {
        // write your code here
        int m = grid.length;
        if (m == 0){
            return 0;
        }
        int n = grid[0].length;
        if(n == 0){
            return 0;
        }
        int old = 0;
        int now = 0;
        int[][] dp = new int[2][n];//create 2 rows of array
        for (int i = 0; i < m; i++){
            old = now;
            now = 1 - now;
            for (int j = 0; j < n; j++){
                if (i == 0 && j == 0){
                    dp[now][j] = grid[i][j];
                    continue;
                }
                int t = Integer.MAX_VALUE;//求最小路径 --> 设初值为maxValue
                if(i > 0){
                    t = Math.min(dp[old][j], t);
                }
                if (j > 0){
                    t = Math.min(dp[now][j - 1], t);
                }

                dp[now][j] = t + grid[i][j];
            }
        }
        return dp[now][n - 1];
    }

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
     * linkCode392.打劫房屋
     * 假设你是一个专业的窃贼，准备沿着一条街打劫房屋。每个房子都存放着特定金额的钱。
     * 你面临的唯一约束条件是：相邻的房子装着相互联系的防盗系统，且 当相邻的两个房子同一天被打劫时，该系统会自动报警。
     *
     * 给定一个非负整数列表，表示每个房子中存放的钱， 算一算，如果今晚去打劫，在不触动报警装置的情况下, 你最多可以得到多少钱
     * 输入: [3, 8, 4]
     * 输出: 8
     * 解释: 仅仅打劫第二个房子.
     *
     * 输入: [5, 2, 1, 3]
     * 输出: 8
     * 解释: 抢第一个和最后一个房子
     *
     * 转移方程: f[i]表示偷前i栋房子的最大收益，最后一房子偷or不偷两种情况
     *          f[i] = max(f[i - 1], f[i - 2] + A[i])
     * */
    public int houseRobber(int[] A) {
        // write your code here
        int n = A.length;
        if (n == 0){
            return 0;
        }
        int[] dp = new int[n + 1];//返回值为long类型
        dp[0] = 0;
        dp[1] = A[0];
        for(int i = 2; i <= n; i++){
            dp[i] = Math.max(dp[i - 1], A[i - 1] + dp[i - 2]);
        }

        return dp[n];
    }

    /**
     * 房子成圈，收尾相连, 0 1 2 3 ... n-1
     * 分为两种情况: 1. 第0个房子不偷
     *             2. 第n-1个房子不偷
     */
    public int houseRobber2(int[] nums) {
        // write your code here
        if (nums.length == 0){
            return 0;
        }

        if (nums.length == 1){
            return nums[0];
        }
        int[] A = new int[nums.length - 1];
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - 1; i++){
            A[i] = nums[i];// 0 -> n - 2
        }
        res = Math.max(houseRobber(A), res);
        for (int i = 0; i < nums.length - 1; i++){
            A[i] = nums[i + 1];// 1 -> n - 1
        }
        res = Math.max(res, houseRobber(A));

        return res;

    }

    /**
     * linkCode76. 最长上升子序列 【坐标型coordinator type】
     * 给定一个整数数组（下标从 0 到 n-1， n 表示整个数组的规模），请找出该数组中的最长上升连续子序列。
     * （最长上升连续子序列可以定义为从右到左或从左到右的序列。）
     *  输入: [4,2,4,5,3,7]
     * 	输出:  4
     *
     * 	解释:
     * 	LIS 是 [2,4,5,7]
     * 1.确定状态
     *          原问题 求以array[i]结尾的数组中的最长上升子序列
     *          子问题 求以array[i - 1]结尾的数组中的最长上升连续子序列
     * 2.转移方程
     *          设f[i]表示能到该位置的最长上升子序列的长度
     *          f[i] = f[j - 1] + 1 && j为枚举0 -> i中的值： i > j  && A[i] > A[j]
     * 3.初始条件和边界
     *          f[0] = 1;
     *
     * 4.计算顺序
     *      从左往右
     * */
    public int longestIncreasingSubsequence(int[] nums) {
        // write your code here
        int n = nums.length;
        if (n == 0){
            return 0;
        }
        int[] dp = new int[n];
        //记录路径
        //int p = 0;//最后的记录
        //int[] pi = new int[n];

        int res = 0;
        for(int i = 0; i < n; i++){
            dp[i] = 1;

            for (int j = 0; j < i; j++){
                if (nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);

                }
            }

            res = Math.max(res, dp[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        DynamicPlan dp = new DynamicPlan();
        //1.Integer数组(1 ~ 24)生成   2.将Integer[]类型转为int[]类型
        Integer[] list = Stream.iterate(1, n -> n + 1).limit(24).toArray(Integer[]::new);
        int[] nums = Arrays.stream(list).mapToInt(Integer::intValue).toArray();
        int count = dp.kSum(nums, 4, 24);
        System.out.println(count);
    }
    /**
     * 对比：背包问题 line508
     * linkCode.89 k数之和
     * 输入：24，找出四个数字之和 = 24    输出：47种方案
     * */
    public int kSum(int A[], int k, int target) {
        int n = A.length;
        //dp[i][j][k]表示从前i个数中，取j个和为k的数的方案数目
        int[][][] dp = new int[n + 1][k + 1][target + 1];
        for (int i = 0; i <= n; i++){
            dp[i][0][0] = 1;
        }

        for(int i = 1; i <= n; i++){
            for (int j = 1; j <= k && j <= i;j++ ){
                for (int t = 1; t <= target ;t++){
                    dp[i][j][t] = dp[i - 1][j][t];
                    if(t >= A[i - 1]){
                        dp[i][j][t] += dp[i - 1][j - 1][t - A[i - 1]];
                    }
                }
            }
        }

        return dp[n][k][target];
    }

    /**
     * linkCode118. 不同的子序列, 给定字符串 S 和 T, 计算 S 的所有子序列中有多少个 T.
     * 输入: S = "rabbbit", T = "rabbit"
     * 输出: 3
     * 解释: 你可以删除 S 中的任意一个 'b', 所以一共有 3 种方式得到 T.
     *
     * 输入: S = "abcd", T = ""
     * 输出: 1
     * 解释: 只有删除 S 中的所有字符这一种方式得到 T
     * */
    public int numDistinct(String S, String T) {
        // write your code here
        int m = S.length();
        if(m == 0){
            return 0;
        }
        int n = T.length();
        if(n == 0){
            return 1;
        }

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m  ;i++ ){
            dp[i][0] = 1;//初始化 当T为空时，则为1
        }

        for (int i = 0; i <= m ; i++ ){
            for (int j = 1; j <= n ; j++ ){
                if(i == 0){
                    dp[i][j] = 0;
                    continue;
                }
                //dp[i][j]表示S[0...i - 1]的子序列中包含T[0...j - 1]的个数
                //由两部分组成
                dp[i][j] = dp[i - 1][j];
                if(S.charAt(i - 1) == T.charAt(j - 1)){
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
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

    /**
     * ‼️背包问题中，dp数组大小和总承重有关系‼️
     * linkCode92. 背包问题
     * 在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i]
     * m:最大承重
     *
     * 确定状态：dp[i][w] 表示前i个物品是否能拼出重量w
     * 转移方程：dp[i][w] = dp[i - 1][w] || dp[i - 1][w - A[i]]
     *
     * */
    public int backPack(int m, int[] A) {
        // write your code here
        if (A.length == 0){
            return 0;
        }
        int n = A.length;
        boolean[][] dp = new boolean[n + 1][m + 1];
        //初始化
        dp[0][0] = true;
        for(int i = 1; i < n + 1; i++){
            dp[0][i] = false;
        }

        for(int i = 1; i < n + 1; i++){
            for(int j = 0; j < m + 1; j++){
                dp[i][j] = dp[i - 1][j];
                if(j >= A[i - 1]){
                    dp[i][j] |= dp[i - 1][j - A[i - 1]];
                }
            }
        }

        int res = 0;
        for (int k = m; k >= 0; k--){
            if(dp[n][k]){
                res = k;
                break;
            }
        }

        return res;
    }


}
