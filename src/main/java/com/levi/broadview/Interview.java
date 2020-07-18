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
        System.out.println(res);
    }

    /**
     * 63题：股票的最大利润
     * 输入：{9, 11, 8, 5, 7, 12, 16, 14}
     * 输入：16 - 5 = 11;
     */
    public static int maxDiff(int[] prices) {
        int len = prices.length;
        int[] lMin = new int[len];
        lMin[0] = prices[0];
        for (int i = 1; i < len; i++) {
            lMin[i] = Math.min(lMin[i - 1], prices[i]);
        }

        int res = 0;
        int k = 0;
        while (k < len) {
            res = Math.max(res, Math.abs(lMin[k] - prices[k]));
            k++;
        }

        return res;
    }
}
