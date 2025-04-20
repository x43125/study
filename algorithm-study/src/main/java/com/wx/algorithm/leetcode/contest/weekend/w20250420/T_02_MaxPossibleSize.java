package com.wx.algorithm.leetcode.contest.weekend.w20250420;

public class T_02_MaxPossibleSize {

    public int maximumPossibleSize(int[] nums) {
        int max = 0;
        int count = 0;
        for (int num : nums) {
            if (max <= num) {
                max = num;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // int[] nums = {4,2,5,3,5};
        // int[] nums = {1,2,3};
        int[] nums = {3,2,1};
        T_02_MaxPossibleSize maxPossibleSize = new T_02_MaxPossibleSize();
        int maximumPossibleSize = maxPossibleSize.maximumPossibleSize(nums);
        System.out.println(maximumPossibleSize);
    }
}
