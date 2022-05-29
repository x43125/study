package com.wx.algorithm.leetcode.contest.weekend.w0529;

import java.util.ArrayDeque;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/29
 */
public class RemoveMinus {
    public int totalSteps(int[] nums) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int[] dp = new int[nums.length];
        int max = 0;
        for (int i = nums.length - 1; i >= 0; deque.push(i--)) {
            while (!deque.isEmpty() && nums[deque.peek()] < nums[i]) {
                max = Math.max(max, dp[i] = Math.max(dp[i] + 1, dp[deque.pop()]));
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = {5, 3, 4, 4, 7, 3, 6, 11, 8, 5, 11};
        RemoveMinus minus = new RemoveMinus();
        int res = minus.totalSteps(nums);
        System.out.println("res = " + res);
    }
}
