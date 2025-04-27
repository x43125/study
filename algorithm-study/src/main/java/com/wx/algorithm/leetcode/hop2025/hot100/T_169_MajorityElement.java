package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_169_MajorityElement {
    public int majorityElement(int[] nums) {
        // 其他的数加起来最多也只有n/2 - 1
        int count = 0;
        int candidate = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count++;
            }

            count += nums[i] == candidate ? 1 : -1;
        }

        return candidate;
    }

    public static void main(String[] args) {
        // int[] nums = { 1, 2, 3, 1, 1,2,2,1,1,1,1};
        int[] nums = { 1, 2,2};
        T_169_MajorityElement majorityElement = new T_169_MajorityElement();
        int majority = majorityElement.majorityElement(nums);
        System.out.println(majority);
    }
}
