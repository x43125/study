package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_283_MoveZeros {

    public void moveZeroes(int[] nums) {
        int i = 0, j = 0;
        while(j < nums.length) {
            if (nums[j] != 0) {
                nums[i] = nums[j];
                nums[j] = 0;
                i++;
            }
            j++;
        }
    }

    public static void main(String[] args) {
        int[] nums = { 0, 1, 0, 3, 12 };
        T_283_MoveZeros moveZeros = new T_283_MoveZeros();
        moveZeros.moveZeroes(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
