package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_198_Rob {
    public int rob(int[] nums) {
        // 打家劫舍
        // 不能连续选中两个，最终选出总和最大的一系列数
        // 1,2,3,1 -> 4
        // < 2 
        // > 2 dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i])
        // int[] dp = new int[nums.length];
        // dp[0] = nums[0];
        // dp[1] = Math.max(nums[0], nums[1]);
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length < 3) {
            return Math.max(nums[0], nums[1]);
        } 
        int first = nums[0], sec = Math.max(nums[0], nums[1]);
        int now = 0;
        for (int i = 2; i < nums.length; i++) {
            now = Math.max(sec, first + nums[i]);
            first = sec;
            sec = now;
        }
        return now;
    }

    public static void main(String[] args) {
        int[] nums = {2,1,1,2};
        T_198_Rob rob = new T_198_Rob();
        int max = rob.rob(nums);
        System.out.println(max);
    }
}
