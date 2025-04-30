package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_300_LongestSequence {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j <= i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 6, 7, 9, 4, 10, 5, 6 };
        T_300_LongestSequence longestSequence = new T_300_LongestSequence();
        int length = longestSequence.lengthOfLIS(nums);
        System.out.println(length);
    }
}
