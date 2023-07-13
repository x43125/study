package com.wx.algorithm.Java.sort;

/**
 * @author Shawn
 * @date 2023/7/14 00:05
 * @description
 */
public class MergeSortSelf {

    public static void main(String[] args) {
        int[] nums = {2, 7, 2, 5, 1, 9, 4, 9, 5, 7, 3, 3};
        sort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    private static void sort(int[] nums) {
        if (nums == null) {
            return;
        }

        mergeSort(nums, 0, nums.length - 1);
    }

    private static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    /**
     * 合并两个有序数组
     *
     * @param nums
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, y = 0;

        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                temp[y++] = nums[i++];
            } else {
                temp[y++] = nums[j++];
            }
        }

        while (i <= mid) {
            temp[y++] = nums[i++];
        }
        while (j <= right) {
            temp[y++] = nums[j++];
        }

        for (int k = left; k <= right; k++) {
            nums[k] = temp[k - left];
        }
    }
}
