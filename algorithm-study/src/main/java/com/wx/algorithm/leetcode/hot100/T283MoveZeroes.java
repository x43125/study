package com.wx.algorithm.leetcode.hot100;

/**
 * @author wangxiang
 * @date 2023/6/17 08:50
 * @description
 */
public class T283MoveZeroes {
    public static void main(String[] args) {
//        int[] nums = {0,1,0,3,12};
//        int[] nums = {0};
//        int[] nums = {0, 0, 0, 1, 0, 0, 0};
        int[] nums = {1};
        moveZeroes(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /**
     * 双指针，从0开始，
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int zero = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[zero];
                nums[zero] = temp;
                zero++;
            }
        }
    }
}
