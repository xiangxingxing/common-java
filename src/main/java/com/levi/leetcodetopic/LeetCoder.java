package com.levi.leetcodetopic;

import javax.swing.plaf.synth.Region;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE && pop > Integer.MAX_VALUE % 10)) {
                return 0;
            }
            //最下 pop < Integer.MIN_VALUE % 10 = -8
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE && pop < Integer.MIN_VALUE % 10)) {
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
        if (x % 10 == 0 && x != 0 || x < 0){
            return false;
        }
        char[] chars = Integer.toString(x).toCharArray();
        if (chars.length == 1){
            return true;
        }
        Stack<Character> stack = new Stack<>();
        int i;
        for (i = 0; i < chars.length >> 1;i++){
            stack.push(chars[i]);
        }

        if ((chars.length & 1) == 1){
            i++;
        }

        while (!stack.isEmpty()){
            if (stack.pop() != chars[i]){
                return false;
            }
            i++;
        }

        return true;
    }

    public static boolean isPalindrome2(int x) {
        //x为非0的10的倍数、负数时，一定不是回文
        if (x % 10 == 0 && x != 0 || x < 0){
            return false;
        }
        char[] chars = Integer.toString(x).toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left <= right){
            if (chars[left] != chars[right]){
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static boolean isPalindrome3(int x) {
        //x为非0的10的倍数、负数时，一定不是回文
        if (x % 10 == 0 && x != 0 || x < 0){
            return false;
        }
        int revertedNumber = 0;
        while (x > revertedNumber){
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        return revertedNumber == x || revertedNumber / 10 == x;
    }

    //leetCode10 实现一个支持 '.' 和 '*' 的正则表达式匹配
    public static boolean isMatch(String s, String p) {
        if (s == null || p.equals(null)){
            return false;
        }

        return matchCore(s, p);
    }

    private static boolean matchCore(String s, String p){
        if (s.isEmpty() && p.isEmpty()){
            return true;
        }
        if (!s.isEmpty() && p.isEmpty()){
            return false;
        }

        if (p.charAt(1) == '*') {
            if (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.' && !s.isEmpty()){
                return matchCore(s.substring(1), p.substring(2))
                        || matchCore(s.substring(1), p)
                        || matchCore(s, p.substring(2));
            }
            else {
                return matchCore(s, p.substring(2));
            }
        }

        if (s.charAt(0) == p.charAt(0) || (p.charAt(0) == '.' && !s.isEmpty())){
            return matchCore(s.substring(1), p.substring(1));
        }
        return false;
    }
}
