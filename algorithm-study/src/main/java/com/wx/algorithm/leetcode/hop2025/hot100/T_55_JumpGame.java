package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_55_JumpGame {

    // 暴力递归，功能没问题，超时了
    public boolean canJump(int[] nums) {
        return canJump(nums, 0);
    }

    private boolean canJump(int[] nums, int i) {
        if (i >= nums.length-1) {
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
        // int[] nums = {2,3,1,1,4};
        int[] nums = {3,2,1,0,4};
        T_55_JumpGame jumpGame = new T_55_JumpGame();
        boolean canJump = jumpGame.canJump(nums);
        System.out.println(canJump);
    }
}
