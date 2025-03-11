package com.wx.algorithm.leetcode.hop2025;

public class T_239_MaxSlideWindow {
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        T_239_MaxSlideWindow maxSlideWindow = new T_239_MaxSlideWindow();
        int[] res = maxSlideWindow.maxSlidingWindow(nums, k);
        for (int max : res) {
            System.out.println(max);
        }
    }

    /**
     * 暴力法
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i+k; j++) {
                max = Math.max(max, nums[j]);
            }
            res[i] = max;
        }
        return res;
    }
}
