package com.levi.dataStructures.linkcode;

/**
 * 动态规划
 *      1.确定状态【最后一步 --> 子问题】
 *      2.转移方程
 *      3.初始条件、边界
 *      4.计算顺序
 * */
public class DynamicPlan {

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
