package com.levi.leetcodetopic;

import java.util.*;

public class LeetCoder {
    //leetCode01
    public static int[] leetCode1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            Integer value = target - nums[i];
            if (map.containsKey(value) && map.get(value) != i) {
                return new int[]{i, map.get(value)};
            }
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    //leetCode02
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pHead = new ListNode(0);
        ListNode current = pHead;
        int carry = 0;//进位

        while (l1 != null || l2 != null) {
            int x = (l1 == null) ? 0 : l1.val;
            int y = (l2 == null) ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            int value = sum % 10;

            current.next = new ListNode(value);
            current = current.next;//后移

            // 条件更新！
            if (l1 != null)
                l1 = l1.next;

            if (l2 != null)
                l2 = l2.next;
        }

        if (carry > 0) {
            current.next = new ListNode(carry);
        }

        return pHead.next;
    }

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

    //leetCode04 寻找两个有序数组的中位数
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int left = 0;
        int right = 0;
        int index = 0;
        int size = nums1.length + nums2.length;
        int[] mergedArray = new int[size];

        while (left < nums1.length && right < nums2.length) {
            if (nums1[left] < nums2[right]) {
                mergedArray[index++] = nums1[left++];
            } else {
                mergedArray[index++] = nums2[right++];
            }
        }

        while (left < nums1.length) {
            mergedArray[index++] = nums1[left++];
        }

        while (right < nums2.length) {
            mergedArray[index++] = nums2[right++];
        }

        double data;
        if (mergedArray.length == 1) {
            data = mergedArray[0];
        } else if ((mergedArray.length & 1) == 0) {
            data = (mergedArray[size / 2] + mergedArray[size / 2 - 1]) / 2.0;
        } else {
            data = mergedArray[size >>> 1];
        }

        return data;
    }

    //leetCode05：找到 s 中最长的回文子串并返回
    public static String longestPalindrome(String s) {
        if (s.length() == 0 || s.isEmpty()) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        //中心扩散法
        int length = 1;
        int startIndex = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                left--;
                length++;
            }

            while (right < s.length() && s.charAt(right) == s.charAt(i)) {
                right++;
                length++;
            }

            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                length += 2;
                left--;
                right++;
            }

            if (length > maxLen) {
                maxLen = length;
                startIndex = left;
            }

            length = 1;//下一个位置前重置
        }

        return s.substring(startIndex + 1, startIndex + 1 + maxLen);//substring:左闭右开
    }

    //leetCode07:给出一个 32 位的有符号整数，将这个整数中每位上的数字进行反转  123 --> 321
    public static int reverse(int x) {

        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            //最大 Integer.MAX_VALUE % 10 = 7
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)) {
                return 0;
            }
            //最下 pop < Integer.MIN_VALUE % 10 = -8
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10)) {
                return 0;
            }
            res = res * 10 + pop;
            x /= 10;
        }

        return res;
    }

    //leetCode08 将字符串转换成整数
    public static int myAtoi(String str) {

        int index = 0;

        //消除空格
        while (index < str.length() && str.charAt(index) == ' ') {
            index++;
        }

        //针对极端："     "
        if (index == str.length()) {
            return 0;
        }

        //处理正负号(如果有的话)
        int signed = 1;//默认为正： '+' -> 1; '-' -> -1
        if (str.charAt(index) == '+') {
            index++;
        } else if (str.charAt(index) == '-') {
            signed = -1;
            index++;
        }

        int res = 0;
        while (index < str.length()) {
            char current = str.charAt(index);
            if (current < '0' || current > '9') {
                break;
            }

            int pop = current - '0';
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (pop > Integer.MAX_VALUE % 10))) {
                return Integer.MAX_VALUE;
            }
            // (-pop) < Integer.MIN_VALUE % 10 注意！
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && ((-pop) < Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }

            res = res * 10 + signed * pop;
            index++;
        }
        return res;

    }

    //leetCode09 判断一个整数是否是回文
    public static boolean isPalindrome(int x) {
        //x为非0的10的倍数、负数时，一定不是回文
        if (x % 10 == 0 && x != 0 || x < 0) {
            return false;
        }
        char[] chars = Integer.toString(x).toCharArray();
        if (chars.length == 1) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        int i;
        for (i = 0; i < chars.length >> 1; i++) {
            stack.push(chars[i]);
        }

        if ((chars.length & 1) == 1) {
            i++;
        }

        while (!stack.isEmpty()) {
            if (stack.pop() != chars[i]) {
                return false;
            }
            i++;
        }

        return true;
    }

    public static boolean isPalindrome2(int x) {
        //x为非0的10的倍数、负数时，一定不是回文
        if (x % 10 == 0 && x != 0 || x < 0) {
            return false;
        }
        char[] chars = Integer.toString(x).toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left <= right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static boolean isPalindrome3(int x) {
        //x为非0的10的倍数、负数时，一定不是回文
        if (x % 10 == 0 && x != 0 || x < 0) {
            return false;
        }
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        return revertedNumber == x || revertedNumber / 10 == x;
    }

    //leetCode10 实现一个支持 '.' 和 '*' 的正则表达式匹配
    public static boolean isMatch(String s, String p) {
        if (s == null || p.equals(null)) {
            return false;
        }

        return matchCore(s, p);
    }

    private static boolean matchCore(String s, String p) {
        if (s.isEmpty() && p.isEmpty()) {
            return true;
        }
        if (!s.isEmpty() && p.isEmpty()) {
            return false;
        }

        if (p.charAt(1) == '*') {
            if (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.' && !s.isEmpty()) {
                return matchCore(s.substring(1), p.substring(2))
                        || matchCore(s.substring(1), p)
                        || matchCore(s, p.substring(2));
            } else {
                return matchCore(s, p.substring(2));
            }
        }

        if (s.charAt(0) == p.charAt(0) || (p.charAt(0) == '.' && !s.isEmpty())) {
            return matchCore(s.substring(1), p.substring(1));
        }
        return false;
    }

    //leetCode11 它们与 x 轴共同构成的容器可以容纳最多的水
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int maxArea = 0;

        while (right > left) {
            int distance = right - left;
            maxArea = Math.max(maxArea, distance * Math.min(height[left], height[right]));

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }

        }
        return maxArea;
    }

    //leetCode12 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};  // 罗马数字
        int[] arab = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};  // 阿拉伯数字
        int index = 0;
        while (num > 0) {
            int count = num / arab[index];
            while (count > 0) {
                sb.append(roman[index]);
                count--;
            }

            num %= arab[index]; //若num小于arab[index]，则取余后仍是本身num
            index++;
        }

        return sb.toString();
    }

    //leetCode13 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
    public static int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("M", 1000);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("CD", 400);
        map.put("C", 100);
        map.put("XC", 90);
        map.put("L", 50);
        map.put("XL", 40);
        map.put("X", 10);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("IV", 4);
        map.put("I", 1);

        if (map.containsKey(s)) {
            return map.get(s);
        }

        int index = 0;
        int res = 0;
        while (index < s.length()) {
            if ((index + 1) < s.length() && map.containsKey(s.substring(index, index + 2))) {
                res += map.get(s.substring(index, index + 2));
                index += 2;
            } else {
                res += map.get(s.substring(index, index + 1));
                index++;
            }
        }
        return res;
    }

    //leetCode14 编写一个函数来查找字符串数组中的最长公共前缀
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j;
            for (j = 0; j < prefix.length() && j < strs[i].length(); j++) {
                if (prefix.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
            }

            prefix = strs[0].substring(0, j);
        }

        return prefix;
    }

    //leetCode15 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0
    //答案中不可以包含重复的三元组
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        if (nums == null || nums.length < 3) {
            return ans;
        }
        Arrays.sort(nums);//排序
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //去掉重复
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else
                    right--;

            }
        }
        return ans;
    }

    //leetCode16 找出 nums 中的三个整数,使得它们的和与target最接近,返回这三个数的和
    public static int threeSumClosest(int[] nums, int target) {
        //先排序
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int pivot = nums[i] + nums[start] + nums[end];
                sum = Math.abs(sum - target) < Math.abs(pivot - target) ? sum : pivot;
                if (pivot > target) {
                    end--;
                } else {
                    start++;
                }
            }
        }

        return sum;
    }

    //leetCode17 电话号码的字母组合：给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合
    public static List<String> letterCombinations(String digits) {
        LinkedList<String> list = new LinkedList<>();
        if (digits.isEmpty()) {
            return list;
        }
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        list.add("");
        for (int i = 0; i < digits.length(); i++) {
            int digit = Character.getNumericValue(digits.charAt(i));
            while (list.peek().length() == i) {
                String temp = list.remove();//linkList 头删法
                for (char s : mapping[digit].toCharArray()) {
                    list.add(temp + s);// linklist 尾插法
                }
            }
        }

        return list;
    }
    //leetCode18


    //leetCode19 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode ahead = head;

        if (n == 1) {
            if (ahead.next == null) {
                return null;
            }
            while (ahead.next.next != null) {
                ahead = ahead.next;
            }
            ahead.next = null;
            return head;
        }

        int i = 0;
        while (i < n - 1) {
            if (ahead.next != null) {
                ahead = ahead.next;
                i++;
            } else {
                return null;
            }
        }

        ListNode behind = head;
        while (ahead.next != null) {
            ahead = ahead.next;
            behind = behind.next;
        }
        //此时behind为倒数第n(n > 1)个节点

        ListNode pNext = behind.next;
        behind.val = pNext.val;
        behind.next = pNext.next;
        pNext.next = null;
        return head;

    }


    //leetCode20 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
    public static boolean isValid(String s) {
        if ((s.length() & 1) == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            switch (s.charAt(i)) {
                case '(':
                    stack.push('(');
                    break;
                case '{':
                    stack.push('{');
                    break;
                case '[':
                    stack.push('[');
                    break;
                case ')':
                    if (!stack.isEmpty() && stack.pop().equals('(')){
                        break;
                    }

                    return false;
                case '}':
                    if (!stack.isEmpty() && stack.pop().equals('{')){
                        break;
                    }

                    return false;
                case ']':
                    if (!stack.isEmpty() && stack.pop().equals('[')){
                        break;
                    }

                    return false;
            }

            i++;
        }
        if (stack.isEmpty()) {
            return true;
        }

        return false;
    }

    //leetCode21 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode pMerged;
        if (l1.val < l2.val) {
            pMerged = l1;
            pMerged.next = mergeTwoLists(l1.next, l2);
        } else {
            pMerged = l2;
            pMerged.next = mergeTwoLists(l1, l2.next);
        }

        return pMerged;
    }

    //leetCode22 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if(n == 0){
            return res;
        }
        dfs("", n,n,res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号还有几个可以使用
     * @param right  右括号还有几个可以使用
     * @param res    结果集
     */
    private static void dfs(String curStr, int left, int right, List<String> res) {
        if(left == 0 && right == 0){
            res.add(curStr);
            return;
        }

        if(left > right){
            return;
        }

        if (left > 0){
            dfs(curStr.concat("("), left - 1, right, res);
        }

        if(right > 0){
            dfs(curStr.concat(")"),left, right -1,res);
        }
    }

    //leetCode24 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
    //不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
    public static ListNode swapPairs(ListNode head) {
        ListNode current = head;
        if (current == null) {
            return null;
        }
        ListNode pNext = current.next;
        ListNode reversed = current;
        ListNode temp;
        if (pNext != null) {
            reversed = pNext;

            current.next = null;
            temp = pNext.next;
            pNext.next = current;
            current.next = swapPairs(temp);
        }

        return reversed;
    }

    //leetCode25 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
    //‼️双指针法‼️
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);//设置头结点
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end != null){
            for (int i = 0; i < k && end != null;i++){
                end = end.next;
            }
            if (end == null){
                break;
            }

            ListNode start = pre.next;//第一个节点
            ListNode next = end.next;//保存第k+1结点
            end.next = null;//断链
            pre.next = reverse(start);//start成为最后一个节点了
            start.next = next;//连接

            pre = start;
            end = start;
        }

        return dummy.next;

    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }

        return pre;
    }

    //leetCode26 原地删除排序数组中的重复项,返回移除后数组的新长度。
    public static int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 1;
        while (j < nums.length){
            if (nums[i] != nums[j]){
                nums[++i] = nums[j];
            }

            j++;
        }

        return i + 1;
    }

    //leetCode27 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
    public static int removeElement(int[] nums, int val) {
        int ans = 0;
        for (int num : nums){
            if (num != val){
                nums[ans++] = num;
            }
        }

        return ans;
    }

    //leetCode28  给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
    // 如果不存在，则返回  -1。

    public static int strStr(String haystack, String needle) {
        if (needle == null || needle.equals("")){
            return 0;
        }
        if(!haystack.contains(needle)){
            return -1;
        }
        return haystack.indexOf(needle);

    }

    //leetCode33 假设按照升序排序的数组在预先未知的某个点上进行了旋转。搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high){
            int mid = (low + high) >> 1;
            int midVal = nums[mid];
            if (target == midVal){
                return mid;
            }
            else if (midVal >= nums[low]){
                if (target >= nums[low] && target < midVal){
                    high = mid - 1;
                }
                else {
                    low = mid+1;
                }
            }
            else {
                if (target <= nums[high] && target > midVal){
                    low = mid+1;
                }
                else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }

    //leetCode34 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
    public static int[] searchRange(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int left = -1, right = -1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (nums[mid] == target) {
                for (left = mid; left > low; ) {
                    if (nums[left] == nums[left - 1]) {
                        left--;
                    }
                    else {
                        break;
                    }
                }

                for (right = mid; right < high; ) {
                    if (nums[right] == nums[right + 1]) {
                        right++;
                    }
                    else {
                        break;
                    }
                }
                break;

            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return new int[]{left, right};
    }

    //leetCode35 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
    //假设数组中无重复元素
    public static int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        //上述二分法没找到target，则插入
        int index = 0;
        while (index < nums.length) {
            if (nums[index] > target) {
                break;
            }
            index++;
        }

        return index;
    }
}
