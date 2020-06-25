package com.levi.leetcodetopic;

import com.levi.dataStructures.tree.MyTreeNode;

import java.util.*;

public class LeetReview {
    //leetCode03 不含有重复字符的 最长子串 的长度
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
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

    //leetCode05：找到 s 中最长的回文子串并返回
    /*
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

    /*
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
}
