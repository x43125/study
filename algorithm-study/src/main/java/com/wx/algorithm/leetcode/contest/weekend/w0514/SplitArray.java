package com.wx.algorithm.leetcode.contest.weekend.w0514;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/14
 */
public class SplitArray {
    public static int waysToSplitArray(int[] nums) {
        // 注意求和的边界问题
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }

        long leftSum = 0;
        int res = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            leftSum += nums[i];
            if (leftSum >= (sum - leftSum)) {
                res++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,0};
        int i = waysToSplitArray(nums);
        System.out.println(i);
    }
}
