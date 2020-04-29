package LeetCodeTopic;

import com.levi.leetcodetopic.LeetCoder;
import com.levi.leetcodetopic.ListNode;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCoderTest {
    @Test
    public void LeetCode1_Test(){
        int[] nums = {2, 7, 11, 15, 4, 9};
        int[] actual = LeetCoder.leetCode1(nums, 20);
        int[] expected = {2,5};
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void LeetCode2_Test(){
        // 2 -> 5 --> 8
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(5);
        l1.next.next = new ListNode(8);

        // 3 --> 6 --> 9
        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(9);

        // 5 --> 1 --> 8 --> 1
        ListNode result = LeetCoder.addTwoNumbers(l1, l2);
        int[] expected = {5,1,8,1};
        for (int i =0; result != null; i++){
            Assert.assertEquals(expected[i], result.val);
            result = result.next;
        }
    }

    @Test
    public void LeetCode3_Test(){
        int result0 = LeetCoder.lengthOfLongestSubstring("arabcacfr");
        int result1 = LeetCoder.lengthOfLongestSubstring("bacab");
        int result2 = LeetCoder.lengthOfLongestSubstring("paneopp");

        Assert.assertEquals(4, result0);
        Assert.assertEquals(3, result1);
        Assert.assertEquals(5, result2);
    }

    @Test
    public void LeetCode4_Test(){
        int[] nums1 = {};
        int[] nums2 = {2,3,4,5,6,7};
        int[] array = Arrays.copyOfRange(nums2, 0, 3);

        double res = LeetCoder.findMedianSortedArrays(nums1, nums2);
        System.out.println(res);

    }

    @Test
    public void LeetCode5_Test(){
        String str = "cqzdabade";
        String expected = "dabad";
        String result = LeetCoder.longestPalindrome(str);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void LeetCode7_Test(){
        int validX = 7425763;
        int expected = 3675247;
        int res = LeetCoder.reverse(validX);
        Assert.assertEquals(expected, res);

        int validY = -7425763;
        int expectedY = -3675247;
        int res1 = LeetCoder.reverse(validY);
        Assert.assertEquals(expectedY, res1);

        int invalidY = 2147483647;
        int expectedY2 = 0;
        int res2 = LeetCoder.reverse(invalidY);
        Assert.assertEquals(expectedY2,res2);
    }

    @Test
    public void LeetCode8_Test(){
        String x1 = "   -42";
        int y1 = LeetCoder.myAtoi(x1);
        int expected1 = -42;
        Assert.assertEquals(expected1, y1);

        String x2 = "4193 with words";
        int y2 = LeetCoder.myAtoi(x2);
        int expected2 = 4193;
        Assert.assertEquals(expected2, y2);

        String x3 = "words and 987";
        int y3 = LeetCoder.myAtoi(x3);
        int expected3 = 0;
        Assert.assertEquals(expected3, y3);

        String x4 = "-91283472332";
        int y4 = LeetCoder.myAtoi(x4);
        int expected4 = -2147483648;
        Assert.assertEquals(expected4, y4);

        String x5 = "-2147483649";
        int y5 = LeetCoder.myAtoi(x5);
        int expected5 = -2147483648;
        Assert.assertEquals(expected5, y5);

    }

    @Test
    public void LeetCode9_Test(){
        int input1 = 12321;
        boolean isPal1 = LeetCoder.isPalindrome2(input1);
        Assert.assertEquals(true, isPal1);

        int input2 = -12321;
        boolean isPal2 = LeetCoder.isPalindrome2(input2);
        Assert.assertEquals(false, isPal2);
    }

    @Test
    @Ignore("do this later.")
    public void LeetCode10_Test(){
        String s = "aaa";
        String p = "a.a";
        boolean res = LeetCoder.isMatch(s, p);
        Assert.assertEquals(true, res);
    }

    @Test
    public void LeetCode11_Test(){
        int[] heights = {1,8,6,2,5,4,8,3,7};
        int result = LeetCoder.maxArea(heights);
        Assert.assertEquals(49, result);
    }

    @Test
    public void LeetCode12_Test(){
        int input1 = 1994;
        String res1 = LeetCoder.intToRoman(input1);
        Assert.assertEquals("MCMXCIV", res1);

        int input2 = 58;
        String res2 = LeetCoder.intToRoman(input2);
        Assert.assertEquals("LVIII", res2);
    }

    @Test
    public void LeetCode13_Test(){
        String input0 = "IV";
        int res0 = LeetCoder.romanToInt(input0);
        Assert.assertEquals(4, res0);

        String input1 = "LVIII";
        int res1 = LeetCoder.romanToInt(input1);
        Assert.assertEquals(58, res1);

        String input2 = "MCMXCIV";
        int res2 = LeetCoder.romanToInt(input2);
        Assert.assertEquals(1994, res2);
    }

    @Test
    public void LeetCode14_Test(){
        String[] strings = {"flower","flow","flight"};
        String res = LeetCoder.longestCommonPrefix(strings);
        Assert.assertEquals("fl", res);

        String[] strings1 = {"dog","racecar","car"};
        String res1 = LeetCoder.longestCommonPrefix(strings1);
        Assert.assertEquals("", res1);
    }

    @Test
    public void LeetCode15_Test(){
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = LeetCoder.threeSum(nums);
        Integer[] expected2 = {-1, 0, 1};
        Integer[] expected1 = {-1,-1, 2};
        Integer[] actual1 = lists.get(0).toArray(new Integer[3]);

        Integer[] actual2 = lists.get(1).toArray(new Integer[3]);

        Assert.assertArrayEquals(expected1, actual1);
        Assert.assertArrayEquals(expected2, actual2);
    }

    @Test
    public void LeetCode16_Test(){
        int[] nums = {-1,2,1,-4};
        int res = LeetCoder.threeSumClosest(nums, 1);
        Assert.assertEquals(2,res);
    }

    @Test
    public void LeetCode17_Test(){
        List<String> list = LeetCoder.letterCombinations("23");
        String[] expected = {"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"};
        String[] res = list.toArray(new String[0]);
        Assert.assertArrayEquals(expected,res);
    }

    @Test
    public void LeetCode20_Test(){
        String str = "({})";
        boolean isValid= LeetCoder.isValid(str);
        String str2 = "([)]";
        boolean isValid2= LeetCoder.isValid(str2);

        Assert.assertEquals(true, isValid);
        Assert.assertEquals(false, isValid2);
    }

    @Test
    public void leetCode21_Test(){
        // 1 -> 2 --> 4
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        // 1 --> 3 --> 4
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode merged = LeetCoder.mergeTwoLists(l1, l2);
        int[] expected = {1,1,2,3,4,4};
        for (int i = 0; merged != null; i++){
            Assert.assertEquals(expected[i], merged.val);
            merged = merged.next;
        }
    }

    @Test
    public void leetCode22_Test(){
        List<String> list = LeetCoder.generateParenthesis(3);
        String[] expected = {
                "((()))",
                "(()())",
                "(())()",
                "()(())",
                "()()()"};
        Assert.assertArrayEquals(expected, list.toArray());

        List<String> list2 = LeetCoder.generateParenthesis(2);
        String[] expected2 = {"(())","()()"};
        Assert.assertArrayEquals(expected2, list2.toArray());
    }

    @Test
    public void leetCode24_Test(){
        // 2 -> 5 --> 8
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(5);
        l1.next.next = new ListNode(8);
        LeetCoder.swapPairs(l1);
    }

    @Test
    public void leetCode26_Test(){
        int[] nums = {1,2,2,3,3,4,5,5,5};
        int length = LeetCoder.removeDuplicates(nums);
        Assert.assertEquals(5,length);
    }
}
