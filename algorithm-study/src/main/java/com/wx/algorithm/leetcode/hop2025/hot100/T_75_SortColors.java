package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_75_SortColors {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                swap(nums, i, p1);
                ++p1;
            } else if (nums[i] == 0) {
                // 将0后的值替换到i位置，如果0后是排好序的1，则需要再将这个1交换到p1后
                swap(nums, i, p0);
                if (p0 < p1) {
                    swap(nums, i, p1);
                }
                ++p0;
                ++p1;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        // int[] nums = { 2, 1, 0, 1, 2 };
        int[] nums = { 0, 1, 2 };
        T_75_SortColors sortColors = new T_75_SortColors();
        sortColors.sortColors(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
