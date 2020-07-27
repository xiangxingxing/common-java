package com.levi.leetcodetopic;

import com.levi.dataStructures.tree.MyTreeNode;

import java.util.*;

public class LeetReview {
    //leetCode03 不含有重复字符的 最长子串 的长度
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() < 1) {
            return 0;
        }

        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(i, map.get(s.charAt(j)));
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }

        return ans;
    }

    /**
     * 定义dp[i]为以第i个字符结尾的最长不重复子串
     * 确定状态：
     * 转移方程：求dp[i]，与dp[i - 1] + 1 且不能重复
     * 初始条件：dp[0] = 1
     * 计算顺序：从左往右
     */
    public static int lengthOfLongestSubstring2(String s) {
        int n;
        if(s == null || (n = s.length()) == 0){
            return 0;
        }
        char[] array = s.toCharArray();
        int[] dp = new int[n];
        dp[0] = 1;
        int res = 1;
        for(int i = 1; i < n; i++){
            dp[i] = 0;
            if (array[i] == array[i - 1]){
                dp[i] = 1;
                continue;
            }

            int k = i - 1;
            while (k >= 0 && array[k] != array[i]){
                k--;
            }

            dp[i] = Math.min(dp[i - 1] + 1, i - k);
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    //leetCode05：找到 s 中最长的回文子串并返回
    /**
    * 空间换时间，将计算结果暂存起来，避免重复计算
    * */
    public static String longestPalindrome(String s) {
        if (Objects.isNull(s) || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;
        int maxEnd = 0;
        int maxLen = 1;

        /*
         * dp[l][r]表示字符串从 l 到 r 这段是否为回文
         * */
        boolean[][] dp = new boolean[strLen][strLen];
        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }
        }

        return s.substring(maxStart, maxEnd + 1);
    }

    /**
     *   leetCode102 请你返回其按 层序遍历 得到的节点值
     *   leetCode107 反转结果集
     */
    public static List<List<Integer>> levelOrder(MyTreeNode<Integer> root) {
        var list = new ArrayList<List<Integer>>();
        var queue = new LinkedList<MyTreeNode<Integer>>();
        MyTreeNode<Integer> node;
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            var array = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                array.add(node.value);
                if (!Objects.isNull(node.left)) {
                    queue.offer(node.left);
                }
                if (!Objects.isNull(node.right)) {
                    queue.offer(node.right);
                }
            }

            list.add(array);
        }

        var length = list.size();
        int mid = length >>> 1;
        for (int i = 0; i < mid; i++) {
            var temp = list.get(i);
            list.set(i, list.get(length - i - 1));
            list.set(length - i - 1, temp);
        }
        return list;
    }

    /**
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     *
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     *
     * 输出: [1,2,2,3,5,6]
     * */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int l1 = m - 1;
        int l2 = n - 1;
        int len = m + n - 1;
        while (l1 >= 0 && l2 >= 0){
            nums1[len--] = nums1[l1] < nums2[l2] ? nums2[l2--] : nums1[l1--];
        }

        if(l2 > 0){
            System.arraycopy(nums2,0, nums1, 0, l2 + 1);
        }
    }

}
