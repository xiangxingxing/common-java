package LeetCodeTopic;

import com.levi.leetcodetopic.LeetCoder;
import com.levi.leetcodetopic.ListNode;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

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
}
