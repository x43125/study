package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_75_SortColors {

    /**
     * 经典荷兰国旗问题🇳🇱
     * 
     * @param nums
     */
    public void sortColors(int[] nums) {
        // 将遇到的1放在1的后面，1的指针向后一步
        // 将遇到的0和0后的值交换，0的指针向后一步，对交换过来的值做第一步操作
        int l0 = 0, l1 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                swap(nums, i, l1);
                l1++;
            } else if (nums[i] == 0) {
                swap(nums, i, l0);
                if (l0 < l1) {
                    swap(nums, i, l1);
                }
                l0++;
                l1++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 1, 0, 1, 2 };
        // int[] nums = { 0, 1, 2 };
        T_75_SortColors sortColors = new T_75_SortColors();
        sortColors.sortColors(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
