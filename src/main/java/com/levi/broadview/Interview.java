package com.levi.broadview;

import java.util.Arrays;

public class Interview {
    //面试题33 判断输入数组是否为某一二叉搜索树的后序遍历结果
    public static boolean verifySequenceOfBST(int sequence[]) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }

        int root = sequence[sequence.length - 1];
        int i = 0;
        //‼️ i < sequence.length - 1 ‼️
        for (; i < sequence.length - 1; i++) {
            if (sequence[i] > root) {
                break;
            }
        }
        //右子树起始
        int j = i;
        while (j < sequence.length - 1) {
            if (sequence[j] <= root) {
                return false;
            }
            j++;
        }

        boolean left = true;
        if (i > 0) {
            left = verifySequenceOfBST(Arrays.copyOfRange(sequence, 0, i));
        }

        boolean right = true;
        if (i < sequence.length - 1) {
            right = verifySequenceOfBST(Arrays.copyOfRange(sequence, i, sequence.length - 1));
        }

        return left && right;
    }

    public static void main(String[] args) {
        int[] nums = {9, 11, 8, 5, 7, 12, 16, 14};
        int res = maxDiff(nums);
        //System.out.println(res);

        System.out.println(add(19, 91));
    }

    /**
     * 63题：股票的最大利润
     * 输入：{9, 11, 8, 5, 7, 12, 16, 14}
     * 输入：16 - 5 = 11;
     */
    public static int maxDiff(int[] prices) {
        int n = prices.length;
        if (n == 0){
            return 0;
        }
        int res = 0;
        int min = prices[0];
        for (int i = 1; i < n; i++){
            res = Math.max(res, prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        return res;
    }

    /**
     * 48.字符串中最长不重复子串
     *      确定状态：f[i]表示该位置处最长子串长度
     *              求f[i - 1]即i - 1处最长子串长度
     *      转移方程
     *              f[i] = f[i - 1] + 1 | s[i] != s[i - 1]   若s[i] = s[i - 1]，则f[i] = 1
     *
     *      初始化条件
     *              f[0] = 1
     *      计算顺序
     *              从左往右
     *      输入: "pwwkew" "abcab"
     *      输出: 3
     *      解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     * */
    public int lengthOfLongestSubstring(String s) {
        int n;
        if (s == null || (n = s.length()) == 0){
            return 0;
        }
        char[] str = s.toCharArray();
        int[] dp = new int[n];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < n; i++){
            if (str[i] == str[i - 1]){
                dp[i] = 1;
                continue;
            }

            int j = i - 1;
            while (j >= 0 && str[j] != str[i]){
                j--;//循环最后的索引j为与i相同字符的位置
            }

            dp[i] = Math.min(dp[i - 1] + 1, i - j) ;
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    /**
     * 面试题65.求两个整数之和，不使用四则运算符
     * */
    public static int add(int num1, int num2){
        int sum,carry;
        do{
            sum = num1 ^ num2;
            carry = (num1 & num2) << 1;
            num1 = sum;
            num2 = carry;
        }while (carry != 0);

        return sum;
    }

}
