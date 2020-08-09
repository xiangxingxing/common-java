package com.levi.dataStructures.linkcode;

import java.util.*;

public class CommonCode {
    /**
     * linkCode2. n的阶乘尾部的零
     * */
    public long trailingZeros(long n) {
        // write your code here, try to do it without arithmetic operators.
        long res = 0;
        while (n != 0){
            res += n / 5 ;
            n /= 5;
        }

        return res;
    }

    /**
     * linkCode15. 整型数组全排列
     */
    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }
        List<Integer> permutation = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(nums, visited, permutation, res);
        return res;
    }

    private void dfs(int[] nums, boolean[] visited, List<Integer> permutation, List<List<Integer>> res) {
        if (permutation.size() == nums.length) {
            res.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            permutation.add(nums[i]);
            visited[i] = true;
            dfs(nums, visited, permutation, res);
            visited[i] = false;
            permutation.remove(permutation.size() - 1);

        }
    }

    /**
     * linkCode71. 二叉树的锯齿形层次遍历
     * */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // write your code here
        if(root == null){
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        int flag = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> integers = new LinkedList<>();
            while (size > 0){
                TreeNode node = queue.pollFirst();
                if (flag == 1) {
                    integers.offerFirst(node.val);
                } else {
                    integers.offerLast(node.val);
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
                size--;
            }

            flag = (flag != 1) ? 1 : 0;

            res.add(integers);
        }

        return res;

    }

    public static void main(String[] args) {
        CommonCode cc = new CommonCode();
        int[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
        int k = 4;
        int target = 24;
        List<List<Integer>> lists = cc.kSumII(array, k, target);
        System.out.println(lists.size());
    }

    /**
     * linkCode90.给定n个不同的正整数，整数k（1<= k <= n）以及一个目标数字。　　　　
     * 在这n个数里面找出K个数，使得这K个数的和等于目标数字，你需要找出所有满足要求的方案。
     * */
    public List<List<Integer>> kSumII(int[] A, int k, int target) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (A == null || A.length == 0 || k == 0) {
            return result;
        }
        Arrays.sort(A);
        helper(A, 0, k, target, new ArrayList<>(), result);
        return result;
    }

    private void helper(int[] A, int startIndex, int remainCount, int remainTarget, List<Integer> subset, List<List<Integer>> result) {
        if (remainTarget == 0 && remainCount == 0) {
            //‼️必须包一层ArrayList<>‼️
            result.add(new ArrayList<>(subset));
            return;
        }
        if (remainCount == 0) {
            return;
        }
        for (int i = startIndex; i < A.length; i++) {
            if (A[startIndex] > remainTarget) {
                break;
            }
            subset.add(A[i]);
            helper(A, i + 1, remainCount - 1, remainTarget - A[i], subset, result);
            subset.remove(subset.size() - 1);
        }
    }

    /**
     * linkCode697. 判断是否为平方数之和
     * 给一个整数 c, 你需要判断是否存在两个整数 a 和 b 使得 a^2 + b^2 = c.
     * */
    public boolean checkSumOfSquareNumbers(int num) {
        if(num < 0){
            return false;
        }
        for(int i = (int) Math.sqrt(num); i >= 0; i-- ){
            if(i * i == num){
                return true;
            }

            int j = num - i * i;
            int k = (int) Math.sqrt(j);
            if(k * k == j){
                return true;
            }
        }

        return false;
    }

}

class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
