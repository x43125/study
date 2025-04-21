package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_35_SearchInsert {

    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if (target < nums[mid]) {
                r = mid - 1;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }

        return l;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,4,6,8,10};
        int target = 7;
        T_35_SearchInsert searchInsert = new T_35_SearchInsert();
        int index = searchInsert.searchInsert(nums, target);
        System.out.println(index);
    }
}
