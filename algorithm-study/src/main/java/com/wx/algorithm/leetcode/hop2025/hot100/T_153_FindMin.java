package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_153_FindMin {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length-1;
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return nums[l];
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,8,1,2,3};
        T_153_FindMin findMin = new T_153_FindMin();
        int min = findMin.findMin(nums);
        System.out.println(min);
    }
}
