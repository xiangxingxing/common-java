package com.levi.basic;

public class BitOperationMaker {

    //求输入整数的二进制表示中含有1的个数
    public static int NumberOfOne(int num){
        int count = 0;
        int flag = 1;
        while (flag > 0){
            if ((num & flag) > 0){
                count++;
            }

            flag <<= 1;//flag左移1位，int型总共32位
        }

        return count;
    }

}
