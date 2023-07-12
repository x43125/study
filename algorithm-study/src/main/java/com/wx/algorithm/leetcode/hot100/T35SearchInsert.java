package com.wx.algorithm.leetcode.hot100;

/**
 * @author Shawn
 * @date 2023/7/12 08:53
 * @description
 */
public class T35SearchInsert {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 4;

        T35SearchInsert searchInsert = new T35SearchInsert();
        int i = searchInsert.searchInsert(nums, target);
        System.out.println("i = " + i);
    }
}
