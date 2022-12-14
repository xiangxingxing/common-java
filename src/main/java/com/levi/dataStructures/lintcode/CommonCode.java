package com.levi.dataStructures.lintcode;

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
     * 子集问题类似解法：lintCode16/17/18/90
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
     * lintCode60. 搜索插入位置
     * */
    public int searchInsert(int[] A, int target) {
        // write your code here
        int n;
        if((n = A.length) == 0) return 0;
        int low = 0;
        int high = A.length - 1;
        while (low + 1 < high){
            int mid = low + (high - low) / 2;
            if(A[mid] == target) return mid;
            else if(A[mid] > target){
                high = mid;
            }else {
                low = mid;
            }
        }

        if(A[low] >= target){
            return low;
        }

        if(A[high] >= target){
            return high;
        }
        return high + 1;
    }

    /**
     * lintCode62. 搜索旋转排序数组
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

//    public static void main(String[] args) {
//        CommonCode cc = new CommonCode();
//        int[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
//        int k = 4;
//        int target = 24;
//        List<List<Integer>> lists = cc.kSumII(array, k, target);
//        System.out.println(lists.size());
//    }

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
            if (A[i] > remainTarget) {
                break;
            }
            subset.add(A[i]);
            helper(A, i + 1, remainCount - 1, remainTarget - A[i], subset, result);
            subset.remove(subset.size() - 1);
        }
    }

    /**
     * 164. 不同的二叉查找树 II
     * 给出n，生成所有由1...n为节点组成的不同的二叉查找树
     * */
    public List<TreeNode> generateTrees(int n) {
        // write your code here
        return internalGenerate(1, n);
    }

    private List<TreeNode> internalGenerate(int start, int end){
        List<TreeNode> res = new ArrayList<>();
        if(start > end){
            res.add(null);
            return res;
        }

        for (int i = start; i <= end; i++){
            List<TreeNode> left = internalGenerate(start, i - 1);
            List<TreeNode> right = internalGenerate(i + 1, end);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }

        return res;
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
     * Interview48.动态规划解法
     *      dp[i]表示以第i个字母结尾的最长子字符串长度, d表示s.charAt(i - 1)离上一次出现该字符的距离
     *      dp[i] = 1;
     *      转移方程：分为
     *        1. d > dp[i - 1] -> dp[i] += dp[i - 1]
     *        2. d <= dp[i - 1] -> dp[i] += d;
     *      边界条件: dp[1] = 1
     * */
    public int lengthOfLongestSubstring2(String s) {
        int n;
        if(s == null || (n = s.length()) == 0){
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int res = dp[1];
        for(int i = 2; i <= n; i++){
            dp[i] = 1;
            int len = 0;
            int tmp = s.charAt(i - 1);
            for(int j = i - 2; j >= 0; j--){
                if(tmp == s.charAt(j)){
                    break;
                }
                len++;
            }
            if(len <= dp[i - 1]){
                dp[i] += len;
            }else {
                dp[i] += dp[i - 1];
            }

            res = Math.max(res, dp[i]);
        }

        return res;
    }

    /**
     * linkCode471. 最高频的K个单词
     * 给一个单词列表，求出这个列表中出现频次最高的K个单词。
     * */
    class Pair{
        int count;
        String key;
        public Pair(String key, int count){
            this.key = key;
            this.count = count;
        }
    }

    public String[] topKFrequentWords(String[] words, int k) {
        String[] res = new String[k];
        Map<String, Integer> map = new HashMap<>();
        for (String key : words){
            if(map.containsKey(key)){
                map.put(key, map.get(key) + 1);
            }else {
                map.put(key, 1);
            }
        }

        // write your code here
        //PriorityQueue：按一定的比较规则将对象进行堆排序
        PriorityQueue<Pair> queue = new PriorityQueue<>(map.size(), (o1, o2) -> {
            if(o1.count != o2.count){
                return o2.count - o1.count;
            }else{
                return o1.key.compareTo(o2.key);
            }
        });

        for (String s : map.keySet()) {
            queue.offer(new Pair(s, map.get(s)));
        }
        int i = 0;
        while (i < k){
            res[i++] = queue.poll().key;
        }

        return res;
    }

    /**
     * linkCode480. 二叉树的所有路径
     * 给一棵二叉树，找出从根节点到叶子节点的所有路径
     * 输入：{1,2,3,#,5}
     * 输出：["1->2->5","1->3"]
     * 解释：
     *    1
     *  /   \
     * 2     3
     *  \
     *   5
     * */
    public static List<String> binaryTreePaths(TreeNode root) {
        // write your code here
        List<String> paths = new ArrayList<>();
        if (root == null){
            return paths;
        }

        if (root.left == null && root.right == null){
            paths.add(root.val + "");//不能直接paths.add(root.val)
            return paths;
        }

        List<String> leftPaths = binaryTreePaths(root.left);
        List<String> rightPaths = binaryTreePaths(root.right);

        for (String p : leftPaths) {
            paths.add(root.val + "->" + p);
        }

        for (String p : rightPaths) {
            paths.add(root.val + "->" + p);
        }

        return paths;
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

    /**
     * lintCode415. 有效回文串
     * 给定一个字符串，判断其是否为一个回文串。只考虑字母和数字，忽略大小写。
     * */
    public boolean isPalindrome(String s) {
        // write your code here
        if(s == null || s.length() == 0) return true;
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right){
            if(!Character.isLetterOrDigit(chars[left])){
                left++;
            } else if(!Character.isLetterOrDigit(chars[right])){
                right--;
            }else {
                if(Character.toLowerCase(chars[left]) != Character.toLowerCase(chars[right])){
                    return false;
                }else {
                    left++;
                    right--;
                }
            }
        }

        return true;
    }

    /**
     * lintCode627.最长回文串
     * 给出一个包含大小写字母的字符串。求出由这些字母构成的最长的回文串的长度是多少。
     * 数据是大小写敏感的，也就是说，"Aa" 并不会被认为是一个回文串。
     * */
    public int longestPalindrome(String s) {
        HashSet<Character> set = new HashSet<>();
        if(s == null || s.length() == 0) return 0;
        char[] array = s.toCharArray();
        int res = 0;
        for(char c : array){
            if(!set.contains(c)){
                set.add(c);
            }else {
                set.remove(c);
                res += 2;
            }
        }

        res += set.isEmpty() ? 0 : 1;
        return res;
    }

    /**
     * lintCode667. 最长的回文序列
     * 给一字符串 s, 找出在 s 中的最长回文子序列的长度
     * 输入： "bbbab"
     * 输出： 4
     * 解释：
     * 一个可能的最长回文序列为 "bbbb"
     * */
    public int longestPalindromeSubSeq(String str){
        int n;
        if(str == null || (n = str.length()) == 0) return 0;
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--){
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++){
                if(str.charAt(i) == str.charAt(j)){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        String ip = "172.16.254.1";
        CommonCode c = new CommonCode();
        System.out.println(c.validIPAddress(ip));
    }
    /**
     * 校验ip地址
     * */
    String IPV6char = "0123456789abcdefABCDEF";

    public String validIPAddress(String IP) {
        //‼️得加上\\‼️
        String[] ipv4 = IP.split("\\.");
        boolean is_IPV4 = true;
        if(ipv4.length == 4 && numberOfToken(IP, '.') == 3){
            for(int i = 0; i < 4;i++){
                if(!isIPV4Number(ipv4[i])){
                    is_IPV4 = false;
                    break;
                }
            }
        }else{
            is_IPV4 = false;
        }

        if(is_IPV4){
            return "IPv4";
        }

        String[] ipv6 = IP.split(":");
        boolean is_IPV6 = true;
        if(ipv6.length == 8 && numberOfToken(IP, ':') == 7){
            for(int i = 0; i < 8; i++){
                if(!isIPV6Number(ipv6[i])){
                    is_IPV6 = false;
                    break;
                }
            }
        }else{
            is_IPV6 = false;
        }

        if(is_IPV6) return "IPv6";

        return "Neither";
    }

    private int numberOfToken(String ip, char token){
        int num = 0;
        for(int i = 0; i < ip.length(); i++){
            if(ip.charAt(i) == token){
                num++;
            }
        }

        return num;
    }

    private boolean isIPV4Number(String str){
        if(str.equals("") || str.length() > 3){
            return false;
        }
        if(str.length() > 1 && str.charAt(0) == '0'){
            return false;
        }

        int num = 0;
        for(int i = 0; i < str.length(); i++){
            if(!Character.isDigit(str.charAt(i))){
                return false;
            }else{
                num = num * 10 + (str.charAt(i) - '0');
            }

        }

        if(num >= 0 && num <= 256){
            return true;
        }

        return false;
    }

    private boolean isIPV6Number(String str){
        if(str.equals("") || str.length() > 4){
            return false;
        }

        for(int i = 0; i < str.length(); i++){
            if(IPV6char.indexOf(str.charAt(i)) == -1){
                return false;
            }
        }

        return true;
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
            /*即 tail.val = value */
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
        int key, val;
        ListNode next;

        private ListNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
        }
    }
}
