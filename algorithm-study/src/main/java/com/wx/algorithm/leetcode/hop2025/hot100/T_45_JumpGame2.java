package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_45_JumpGame2 {
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int step = 0;
        int maxReach = 0;
        for (int i=0; i<length-1; ++i) {
            maxReach = Math.max(maxReach, i+nums[i]);
            if (i == end) {
                step++;
                end = maxReach;
            }
        }
        return step;
    }

    public static void main(String[] args) {
        int[] nums = {2,2,2,2};
        T_45_JumpGame2 jumpGame2 = new T_45_JumpGame2();
        int jump = jumpGame2.jump(nums);
        System.out.println(jump);
    }
}
