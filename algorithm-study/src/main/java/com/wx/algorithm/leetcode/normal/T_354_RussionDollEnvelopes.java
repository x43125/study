package com.wx.algorithm.leetcode.normal;

import java.util.Arrays;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/30
 */
public class T_354_RussionDollEnvelopes {
    public static int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (int[] a, int[] b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        int[] height = new int[envelopes.length];
        for (int i = 0; i < height.length; i++) {
            height[i] = envelopes[i][1];
        }

        return lengthOfLIS(height);
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

    public static void main(String[] args) {
        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        int res = maxEnvelopes(envelopes);
        System.out.println("res = " + res);
    }
}
