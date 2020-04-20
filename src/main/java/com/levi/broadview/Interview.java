package com.levi.broadview;

import java.util.Arrays;

public class Interview {
    //面试题33 判断输入数组是否为某一二叉搜索树的后序遍历结果
    public static boolean verifySequenceOfBST(int sequence[]) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }

        int root = sequence[sequence.length - 1];
        int i = 0;
        //‼️ i < sequence.length - 1 ‼️
        for (; i < sequence.length - 1; i++) {
            if (sequence[i] > root) {
                break;
            }
        }
        //右子树起始
        int j = i;
        while (j < sequence.length - 1) {
            if (sequence[j] <= root) {
                return false;
            }
            j++;
        }

        boolean left = true;
        if (i > 0) {
            left = verifySequenceOfBST(Arrays.copyOfRange(sequence, 0, i));
        }

        boolean right = true;
        if (i < sequence.length - 1) {
            right = verifySequenceOfBST(Arrays.copyOfRange(sequence, i, sequence.length - 1));
        }

        return left && right;
    }
}
