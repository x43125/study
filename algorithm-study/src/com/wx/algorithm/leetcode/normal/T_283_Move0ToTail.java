package com.wx.algorithm.leetcode.normal;

public class T_283_Move0ToTail {
    public static void moveZeroes(int[] nums) {
        int fast = 0;
        int slow = 0;

        while (fast < nums.length) {
            if (nums[fast] != 0) {
                int temp = nums[slow];
                nums[slow++] = nums[fast];
                nums[fast] = temp;
            }
            fast++;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1};
        moveZeroes(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }

}
