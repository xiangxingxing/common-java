package com.levi.basic;

import java.util.Arrays;

public class BitOperationMaker {
    public static void main(String[] args) {
        //int[] nums = {2,4,6,4,2,6,1};
        //System.out.println(singleNumber(nums));

        //int num = 000000000000000000010101000011;
        //System.out.println(NumberOfOne2(num));

        int[] countBits = countBits(5);
        int[] expected = {0, 1, 1, 2, 1, 2};
        System.out.println(Arrays.equals(countBits, expected));


    }

    //LeetCode191：求输入整数的二进制表示中含有1的个数
    public static int numberOfOne(int num) {
        int count = 0;
        int flag = 1;
        while (flag > 0) {
            if ((num & flag) > 0) {
                count++;
            }

            flag <<= 1;//flag左移1位，int型总共32位
        }

        return count;
    }

    /*
     * 结论：把一个整数减去1，再和原整数做 & 运算，会把该整数最右边的1变成0
     *  --> 一个整数的二进制表示中有多少个1，就可以进行多少次这样的操作
     * */
    public static int numberOfOne2(int num) {
        int count = 0;
        while (num != 0) {
            num &= num - 1;
            count++;
        }

        return count;
    }

    /*
     *   LeetCode338：给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     *   输入: 5
     *   输出: [0,1,1,2,1,2]
     * */
    public static int[] countBits(int num) {
        int[] array = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            array[i] = numberOfOne2(i);
        }

        return array;
    }

    /*
     *   LeetCode136:给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *   输入: [2,2,1]
     *   输出: 1
     * */
    public static int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];//异或满足交换律
        }

        return res;
    }
}
