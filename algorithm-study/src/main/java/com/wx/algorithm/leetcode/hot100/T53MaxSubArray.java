package com.wx.algorithm.leetcode.hot100;

/**
 * @author wangxiang
 * @date 2023/6/21 09:40
 * @description
 */
public class T53MaxSubArray {

    public static void main(String[] args) {
//        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int[] nums = {5,4,-1,7,8};
//        int[] nums = {1};
        int max = maxSubArray(nums);
        System.out.println("max = " + max);
    }

    /**
     * 典型dp
     * 要想: dp[n] = f(dp[n-1]) 的f是什么
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            if (sum + num < num) {
                sum = num;
            } else {
                sum += num;
            }
            max = Math.max(max, sum);
        }

        return max;
    }
}
