package com.wx.algorithm.Java.sort;

/**
 * @author Shawn
 * @date 2023/7/13 23:50
 * @description
 */
public class QuickSortSelf {
    public static void main(String[] args) {
        int[] nums = {2, 7, 2, 5, 1, 9, 4, 9, 5, 7, 3, 3};
        sort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    private static void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left, j = right, x = nums[left];
        while (i < j) {
            while (i < j && nums[j] >= x) {
                j--;
            }
            if (i < j) {
                nums[i] = nums[j];
            }
            while (i < j && nums[i] <= x) {
                i++;
            }
            if (i < j) {
                nums[j] = nums[i];
            }
        }

        nums[i] = x;
        quickSort(nums, 0, left - 1);
        quickSort(nums, left + 1, right);
    }

}
