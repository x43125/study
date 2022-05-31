package com.wx.algorithm.leetcode.normal;

import java.util.Arrays;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/30
 */
public class T_300_LongestSubSequence {
    public static void main(String[] args) {
        int[] nums = {1};
        int res = lengthOfLIS(nums);
        System.out.println("res = " + res);
    }

    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
