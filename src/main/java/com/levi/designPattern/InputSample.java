package com.levi.designPattern;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputSample {
    public static void main(String[] args) {
        //var in = new Scanner(System.in);
//        System.out.print("Enter keyword 1: ");
//        int input = in.nextInt();
//        System.out.print("Enter keyword 2: ");
//        int input2 = in.nextInt();
//
//        int res = input + input2;
//        System.out.println("input1 + input2 = " + res);

        //System.out.print("Enter array: ");
        //String nums = in.nextLine();
        //String[] split = nums.split(",");
        //List<String> list = Arrays.stream(split).collect(Collectors.toList());
        //System.out.println(list.getClass());

        //samp2();

        System.out.printf("%.2f",432.218f) ;

    }

    /**
     * 输入多个数字填充一数组
     * */
    public static void samp1(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数组中元素个数：");
        int n = sc.nextInt();
        int[] arr = new int[n];//把输入的值作为数组长度；
        for (int i = 0; i < n; i++) {
            System.out.print("请输入arr[" + i + "] = ");
            arr[i] = sc.nextInt();//把输入的值依次存入数组中
        }
        sc.close();
        System.out.println("输入的数组元素为：");
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i] + " ");//依次打印数组中元素；

        }
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];//数组元素求和；
        }
        System.out.println("所有元素的平均值为：" + sum / n);
        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 == 0) {
                System.out.println(arr[i] + "是偶数");
            } else {
                System.out.println(arr[i] + "是奇数");
            }
        }
    }

    /**
     * 输入一个矩阵
     * */
    public static void samp2(){
        Scanner reader = new Scanner(System.in);
        int m = reader.nextInt();
        int n = reader.nextInt() ;
        int [][] array = new int[m][n] ;
        for (int i=0 ; i<m ; i++)
            for(int j=0 ;j<n ;j++)
            {
                array[i][j]=reader.nextInt();
            }
        reader.close() ;
        /**
         * 对矩阵按行打出
         */
        for (int i=0 ; i<m ; i++)
        {
            for(int j=0 ;j<n ;j++)
            {
                System.out.print(array[i][j]+" ");
            }
            System.out.println( );
        }
    }



}
