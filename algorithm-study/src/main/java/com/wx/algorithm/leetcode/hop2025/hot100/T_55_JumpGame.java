package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_55_JumpGame {

    public boolean canJump_02(int[] nums) {
        int n = nums.length;
        int rightIndex = 0;
        for (int i = 0; i < n; i++) {
            if (rightIndex < i) {
                return false;
            }
            rightIndex = Math.max(rightIndex, nums[i] + i);
            if (rightIndex >= n - 1) {
                return true;
            }
        }

        return false;
    }

    // 暴力递归，功能没问题，超时了
    public boolean canJump(int[] nums) {
        return canJump(nums, 0);
    }

    private boolean canJump(int[] nums, int i) {
        if (i >= nums.length - 1) {
            return true;
        }

        int start = nums[i];
        for (int j = 1; j <= start; j++) {
            boolean canJump = canJump(nums, i + j);
            if (canJump) {
                return canJump;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 3, 1, 1, 4 };
        // int[] nums = { 3, 2, 1, 0, 4 };
        // int[] nums = { 0 };
        T_55_JumpGame jumpGame = new T_55_JumpGame();
        boolean canJump = jumpGame.canJump_02(nums);
        System.out.println(canJump);
    }
}
