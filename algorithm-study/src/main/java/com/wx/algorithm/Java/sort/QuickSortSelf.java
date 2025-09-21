package com.wx.algorithm.Java.sort;

/**
 * @author Shawn
 * @date 2023/7/13 23:50
 * @description
 */
public class QuickSortSelf {

    private static void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        
        int pivot = partition(nums, left, right);
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        int i = left, j = right, x = nums[left];
        while (true) {
            while (i < j && nums[i] < x) {
                i++;
            }
            while (i < j && nums[j] > x) {
                j--;
            }
            if (i >= j) {
                return i;
            }
            swap(nums, i, j);
        }        
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 1, 0,3};
        sort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
