package com.wx.algorithm.leetcode.contest.weekend.w0605;

import java.util.Arrays;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/06/05
 */
public class MaxGapK {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int start = 0;
        int end = 0;
        int res = 0;
        while (end < nums.length) {
            if (nums[end] - nums[start] > k) {
                res++;
                start = end;
            }
            end++;
        }

        return res + 1;
    }

    public static void main(String[] args) {
//        int[] nums = {2, 4, 2, 5};
        int[] nums = {0, 3, 6, 1, 2, 5};
        int k = 2;

        MaxGapK maxGapK = new MaxGapK();
        int res = maxGapK.partitionArray(nums, k);
        System.out.println("res = " + res);
    }
}
