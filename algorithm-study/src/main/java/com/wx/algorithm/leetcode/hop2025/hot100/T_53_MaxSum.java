package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_53_MaxSum {
    public int maxSubArray(int[] nums) {
        int sum = Integer.MIN_VALUE;
        int pre = 0;
        for (int num : nums) {
            pre = Math.max(num, pre+num);
            sum = Math.max(sum, pre);
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        T_53_MaxSum maxSum = new T_53_MaxSum();
        int sum = maxSum.maxSubArray(nums);
        System.out.println(sum);
    }
}
