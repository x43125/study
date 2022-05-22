package com.wx.algorithm.leetcode.contest.weekend.w0522;

import java.util.Arrays;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/22
 */
public class MaxFullBag {
    public static int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int[] nums = new int[capacity.length];
        for (int i = 0; i < capacity.length; i++) {
            nums[i] = capacity[i] - rocks[i];
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (additionalRocks == 0 || additionalRocks < nums[i]) {
                return i;
            } else if (additionalRocks < 0) {
                return i - 1;
            }

            additionalRocks -= nums[i];
        }
        return nums.length;
//        return additionalRocks >= 0 ? nums.length : nums.length - 1;
    }

    public static void main(String[] args) {
        int[] capacity = {10, 2, 2};
        int[] rocks = {2, 2, 0};
        int additionalRocks = 1;
        System.out.println(maximumBags(capacity, rocks, additionalRocks));
    }

}
