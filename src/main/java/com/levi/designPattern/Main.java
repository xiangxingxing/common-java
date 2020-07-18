package com.levi.designPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
/*
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)){
            int n = sc.nextInt();
            if (n < 4){
                System.out.println(0);
                return;
            }
            int l1 = 0;
            int l2 = 1;
            int fb;
            while (l2 < n){
                fb = l1 + l2;
                l1 = l2;
                l2 = fb;
            }

            int min = Math.min(l2 - n, n - l1);
            System.out.println(min);
        }
    }
*/

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)){
            int m = sc.nextInt();
            int n = sc.nextInt();
            System.out.println(minStep(m, n));
        }
    }

    //获取n的所有公约数，除1和n外
    private static ArrayList<Integer> allFactor(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                list.add(i);
                if (i != n / i) {
                    list.add(n / i);
                }
            }
        }

        return list;
    }

    /**
     * 链接：https://www.nowcoder.com/questionTerminal/4284c8f466814870bae7799a07d49ec8?f=discussion
     * 来源：牛客网
     *
     * 小易来到了一条石板路前，每块石板上从1挨着编号为：1、2、3.......
     * 这条石板路要根据特殊的规则才能前进：对于小易当前所在的编号为K的 石板，小易单次只能往前跳K的一个约数(不含1和K)步，
     * 即跳到K+X(X为K的一个非1和本身的约数)的位置。 小易当前处在编号为N的石板，他想跳到编号恰好为M的石板去，小易想知道最少需要跳跃几次可以到达。
     * 例如：
     * N = 4，M = 24：
     * 4->6->8->12->18->24
     * 于是小易最少需要跳跃5次，就可以从4号石板跳到24号石板
     * */
    private static int minStep(int m, int n){
        int[] mark = new int[n + 1];
        Arrays.fill(mark, -1);
        mark[m] = 0;//从起点到m点需要走0步
        for (int i = m; i < n - 1; i++){
            if (mark[i] != -1){
                ArrayList<Integer> factors = allFactor(i);
                for (int j = 0; j < factors.size(); j++){
                    int index = i + factors.get(j);
                    int count = mark[i] + 1;
                    if (index < n + 1 && mark[index] == -1){
                        mark[index] = count;
                    }

                    else if (index < n + 1 && mark[index] != -1){
                        mark[index] = Math.min(mark[index], count);
                    }

                }
            }
        }

        return mark[n];
    }

}
