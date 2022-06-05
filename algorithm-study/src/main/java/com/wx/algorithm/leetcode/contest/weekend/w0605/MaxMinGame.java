package com.wx.algorithm.leetcode.contest.weekend.w0605;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/06/05
 */
public class MaxMinGame {
    public static void main(String[] args) {
        MaxMinGame game = new MaxMinGame();
//        int[] nums = {1, 3, 5, 2, 4, 8, 2, 2};
        int[] nums = {3};
        int res = game.minMaxGame(nums);
        System.out.println("res = " + res);
    }

    public int minMaxGame(int[] nums) {
        return traverse(nums);
    }

    private int traverse(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.min(nums[0], nums[1]);
        }

        int[] newNums = new int[n / 2];
        for (int i = 0; i < n / 2; i++) {
            newNums[i] = Math.min(nums[2 * i], nums[2 * i + 1]);
            i++;
            newNums[i] = Math.max(nums[2 * i], nums[2 * i + 1]);
        }
        return traverse(newNums);
    }
}
