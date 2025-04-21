package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_34_SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if (nums[mid] == target) {
                l = mid-1;
                while (l >= 0 && nums[l] == target) {
                    l--;
                }
                r = mid+1;
                while (r < nums.length && nums[r] == target) {
                    r++;
                }
                return new int[] { l + 1, r - 1 };
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 2, 2, 3, 4, 5, 6, 7 };
        int target = 2;
        T_34_SearchRange searchRange = new T_34_SearchRange();
        int[] range = searchRange.searchRange(nums, target);
        System.out.println(range[0] + ":" + range[1]);
    }
}
