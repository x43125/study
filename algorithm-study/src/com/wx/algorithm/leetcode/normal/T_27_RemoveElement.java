package com.wx.algorithm.leetcode.normal;

public class T_27_RemoveElement {
    public static int removeElement(int[] nums, int val) {
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] != val) {
                nums[left++] = nums[right];
            }
            right++;
        }

        for (int i = 0; i < left; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        return left;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 2, 2, 3 };
        int k = 3;
        System.out.println(removeElement(nums, k));
    }
}
