package com.wx.algorithm.leetcode.normal;

public class T_26_RemoveDuplicateArray {
    public static int removeDuplicates(int[] nums) {
        int left = 0;
        int right = 0;

        while (right < nums.length) {
            if (nums[left] != nums[right]) {
                left++;
                nums[left] = nums[right];
            }
            right++;
        }
        for (int i = 0; i < left + 1; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        return left + 1;
    }

    public static int removeDuplicates_2(int[] nums) {
        int point = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[point++] = nums[i];
            }
        }

        for (int i = 0; i < point; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        return point;
    }

    public static void main(String[] args) {
        // int[] nums = { 1, 2 };
        int[] nums = { 1, 2, 2, 3, 4, 4, 4, 5 };
        System.out.println(removeDuplicates(nums));
        // System.out.println(removeDuplicates_2(nums));
    }
}
