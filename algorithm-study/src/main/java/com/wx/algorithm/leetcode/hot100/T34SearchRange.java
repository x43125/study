package com.wx.algorithm.leetcode.hot100;

/**
 * @author Shawn
 * @date 2023/7/12 09:49
 * @description
 */
public class T34SearchRange {
    /**
     * 两次二分查找，分别找左右边界
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length - 1;
        int left = 0, right = n;
        int leftIndex = -1, rightIndex = -1;

        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                leftIndex = mid;
                // 向左找，是否还有相同的值
                right = mid - 1;
            }
        }

        left = 0;
        right = n;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                rightIndex = mid;
                // 向右找是否还有相同的值
                left = mid + 1;
            }
        }

        return new int[]{leftIndex, rightIndex};
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        T34SearchRange searchRange = new T34SearchRange();
        int[] range = searchRange.searchRange(nums, target);
        for (int num : range) {
            System.out.print(num + " ");
        }
    }
}
