package com.levi.algorithms.search;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BinarySearcher {
    public static void main(String[] args) {
        int[][] matrix = new int[3][4];
        matrix[0] = new int[]{1, 3, 5, 7};
        matrix[1] = new int[]{10, 11, 16, 20};
        matrix[2] = new int[]{23, 30, 34, 50};

        // [[1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50]]
        System.out.println(Arrays.deepToString(matrix));
    }

    /**
     * 二分查找通用模板
     * linkCode14. 二分查找
     * 给定一个排序的整数数组（升序）和一个要查找的整数target，用O(logn)的时间查找到target第一次出现的下标（从0开始），如果target不存在于数组中，返回-1。
     * */
    public int binarySearch(int[] nums, int target) {
        // write your code here
        int start = 0;
        int end = nums.length - 1;
        //先缩小区间
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;//若重复则返回第一个
                //start = mid;//若重复则返回最后一个
            } else if (nums[mid] < target) {
                start = mid;
            } else if (nums[mid] > target) {
                end = mid;
            }
        }

        //再判断位置
        if (nums[start] == target) {
            return start;
        }

        if (nums[end] == target) {
            return end;
        }

        return -1;
    }

    /**
     * linkCode46.主元素
     * 给定一个整型数组，找出主元素，它在数组中的出现次数严格大于数组元素个数的二分之一
     * */
    public int majorityNumber(List<Integer> nums) {
        // write your code here
        Iterator<Integer> iterator = nums.iterator();
        int count = 0;
        Integer key = null;
        while (iterator.hasNext()){
            int num = iterator.next();
            if(count == 0){
                key = num;
                count = 1;
            }else if(num == key){
                count += 1;
            }else {
                count -= 1;
            }
        }

        return key;
    }

    public static <T extends Comparable<T>> int BinarySearch(T[] array, T target) {
        int low = 0;
        int high = array.length - 1;
        int indexOfKey = BinarySearch(array, low, high, target);
        return indexOfKey;
    }

    private static <T extends Comparable<T>> int BinarySearch(T[] array, int low, int high, T target) {
        //‼️必须是等于号 <= ‼️
        while (low <= high) {
            int mid = (low + high) >>> 1;
            T midVal = array[mid];
            if (midVal.compareTo(target) > 0) {
                high = mid - 1;
            } else if (midVal.compareTo(target) < 0) {
                low = mid + 1;
            } else {
                return mid;//key found
            }
        }

        return -(low + 1);//key not found
    }

    /**
    *   LeetCode74. 搜索二维矩阵【二维数组的二分查找】
     *  每行中的整数从左到右按升序排列。
     *  每行的第一个整数大于前一行的最后一个整数。
    * 输入:
            matrix = [
              [1,   3,  5,  7],
              [10, 11, 16, 20],
              [23, 30, 34, 50]
            ]
            target = 3
            输出: true
    * */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) return false;
        int col = matrix[0].length;

        int low = 0;
        int high = row * col - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int val = matrix[mid / col][mid % col];
            if (val == target) {
                return true;
            } else if (val > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return false;
    }

    /**
     * 方式2
     * 条件：while(low + 1 < high)
     * 赋值：high = mid; low = mid;
     * */
    public boolean searchMatrix2(int[][] matrix, int target) {
        // write your code here
        int row, col;
        if(matrix == null
                || (row = matrix.length) == 0
                || (col = matrix[0].length) == 0){
            return false;
        }

        int low = 0;
        int high = row * col - 1;
        while(low + 1 < high){
            int mid = low + (high - low) / 2;
            int value = matrix[mid / col][mid % col];
            if(value == target){
                high = mid;
            }else if(value < target){
                low = mid;
            }else{
                high = mid;
            }
        }

        if(matrix[low / col][low % col] == target){
            return true;
        }

        if(matrix[high / col][high % col] == target){
            return true;
        }

        return false;
    }

    /**
     * linkCode38. 搜索二维矩阵 II
     * 写出一个‼️高效‼️的算法来搜索m×n矩阵中的值，返回这个值出现的次数
     * 由于不满足条件（每行的第一个整数大于前一行的最后一个整数） -->  从左下角往右上角进行遍历，
     * */
    public int searchCountMatrix(int[][] matrix, int target) {
        // write your code here
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        //从左下角往右上角遍历
        int row = matrix.length - 1;
        int col = matrix[0].length;

        int x = row;
        int y = 0;
        int res = 0;
        while (x >= 0 && y < col){
            if (matrix[x][y] == target){
                res++;
                x--;
                y++;
            }else if(matrix[x][y] < target){
                y++;
            }else {
                x--;
            }
        }

        return res;
    }

    //LeetCode278. 第一个错误的版本
    public int firstBadVersion(int n) {
        int low = 1;
        int high = n;
        /*
         * 在二分查找的每次操作中，我们都用left和right 表示搜索空间的左右边界
         * 因此在初始化时，需要将left 的值设置为 1，并将right 的值设置为n。
         * ‼️当某一次操作后，left 和right 的值相等，此时它们就表示了第一个错误版本的位置‼️
         * */
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (isBadVersion(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;//当 low == high 时返回
    }

    private static boolean isBadVersion(int n) {
        return false;
    }

    /**
     * LeetCode153. 寻找旋转排序数组中的最小值,假设数组中不存在重复元素
     * 输入: [3,4,5,1,2]
     * 输出: 1
     * */
    public static int findMin(int[] nums) {
        if (nums == null || nums.length < 1) {
            throw new RuntimeException("Invalid parameters");
        }

        int low = 0;
        int high = nums.length - 1;
        int mid = low;//nums已排序的情况下，可以直接返回nums[mid]
        while (nums[low] > nums[high]) {
            if (high - low == 1) {
                mid = high;//赋值
                break;
            }
            mid = (low + high) >>> 1;
            if (nums[mid] > nums[low]) {
                low = mid;
            } else if (nums[mid] < nums[high]) {
                high = mid;
            }
        }

        return nums[mid];
    }

    /**
     * LeetCode154. 寻找旋转排序数组中的最小值 II，注意数组中可能存在重复的元素
     * 输入: [2,2,2,0,1]
     * 输出: 0
     * */
    public int findMin2(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int mid = low;
        while (nums[low] >= nums[high]) {
            if (high - low == 1) {
                mid = high;
                break;
            }
            mid = (low + high) >>> 1;
            if (nums[mid] == nums[low] && nums[mid] == nums[high]) {
                return minInOrder(nums, low, high);
            }

            if (nums[mid] >= nums[low]) {
                low = mid;
            } else if (nums[mid] <= nums[high]) {
                high = mid;
            }
        }

        return nums[mid];
    }

    private int minInOrder(int[] nums, int start, int end) {
        int res = nums[start];
        for (int i = start + 1; i <= end; i++) {
            if (res > nums[i]) {
                res = nums[i];
            }
        }

        return res;
    }
}
