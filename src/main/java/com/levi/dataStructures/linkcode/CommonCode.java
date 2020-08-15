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
     * LinkCode5. 第k大元素
     * 在数组中找到第 k 大的元素。
     * */
    public int kthLargestElement(int n, int[] nums) {
        // write your code here
        //第k大元素在排序后的坐标为 nums.length - k;
        int len;
        if(nums == null || (len = nums.length) == 0){
            return -1;
        }
        return partition(nums, 0, len - 1, len - n);

    }

    private int partition(int[] nums, int start, int end, int index){
        int low = start;
        int high = end;
        int tmp = nums[start];
        while(start < end){
            while(start < end && nums[end] >= tmp){
                end--;
            }

            nums[start] = nums[end];
            while(start < end && nums[start] <= tmp){
                start++;
            }

            nums[end] = nums[start];
        }

        nums[start] = tmp;
        if(start == index){
            return nums[start];
        }else if(start < index){
            return partition(nums, start + 1, high, index);
        }else{
            return partition(nums, low, end - 1, index);
        }
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
     * linkCode62. 搜索旋转排序数组
     * 给定一个目标值进行搜索，如果在数组中找到目标值返回数组中的索引位置，否则返回-1。
     * */
    public int search(int[] A, int target) {
        if(A == null || A.length == 0){
            return -1;
        }
        // write your code here
        int low = 0;
        int high = A.length - 1;
        while(low + 1 < high){
            int mid = low + (high - low) / 2;
            if(A[mid] == target){
                return mid;
            }
            if(A[mid] > A[low]){
                if(A[low] <= target && target < A[mid]){
                    high = mid;
                }else{
                    low = mid;
                }
            }else{
                if(A[mid] < target && target <= A[high]){
                    low = mid;
                }else{
                    high = mid;
                }
            }
        }

        if(A[low] == target){
            return low;
        }
        if(A[high] == target){
            return high;
        }

        return -1;
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

            flag ^= 1;

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
     *linkCode384. 最长无重复字符的子串
     * */
    public int lengthOfLongestSubstring(String s) {
        // write your code here
        if(s.length() == 0){
            return 0;
        }

        Set<Character> window = new HashSet<>();
        char[] chars = s.toCharArray();
        int curLen = 0;
        int maxLen = 0;
        int left = 0;
        for (char ch : chars) {
            while (window.contains(ch)){
                window.remove(chars[left++]);
                curLen--;
            }

            window.add(ch);
            curLen++;
            maxLen = Math.max(maxLen, curLen);
        }

        return maxLen;
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


/**
 * linkCode134. LRU缓存策略
 * 为最近最少使用（LRU）缓存策略设计一个数据结构，它应该支持以下操作：获取数据和写入数据。
 * get(key) 获取数据：如果缓存中存在key，则获取其数据值（通常是正数），否则返回-1。
 * set(key, value) 写入数据：如果key还没有在缓存中，则写入其数据值。当缓存达到上限，它应该在写入新数据之前删除最近最少使用的数据用来腾出空闲位置。
 * 最终, 你需要返回每次 get 的数据.
 * */
class LRUCache {
    private HashMap<Integer, ListNode> keyToPrev;
    private int capacity, size;
    private ListNode dummy, tail;

    /*
     * @param capacity: An integer
     */public LRUCache(int capacity) {
        // do intialization if necessary
        keyToPrev = new HashMap<>();
        this.capacity = capacity;
        this.dummy = new ListNode(0,0);
        this.tail = dummy;
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // write your code here
        if (!keyToPrev.containsKey(key)){
            return -1;
        }

        moveToTail(key);
        return tail.val;
    }

    private void moveToTail(int key){
        ListNode pre = keyToPrev.get(key);
        ListNode cur = pre.next;
        if (cur == tail){
            return;
        }

        pre.next = cur.next;
        tail.next = cur;
        cur.next = null;
        if(pre.next != null){
            keyToPrev.put(pre.next.key, pre);
        }
        keyToPrev.put(cur.key, tail);
        tail = cur;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        // write your code here
        if(get(key) != -1){
            ListNode pre = keyToPrev.get(key);
            pre.next.val = value;
            return;
        }

        if(size < capacity){
            size++;
            ListNode cur = new ListNode(key, value);
            tail.next = cur;
            keyToPrev.put(key, tail);
            tail = cur;
            return;
        }

        //key不存在且cache容量已经满了
        ListNode first = dummy.next;
        keyToPrev.remove(first.key);
        first.key = key;
        first.val = value;
        keyToPrev.put(key, dummy);

        moveToTail(key);
    }

    class ListNode {
        public int key, val;
        public ListNode next;

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
        }
    }
}
