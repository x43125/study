package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_33_SearchRotateArr {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        if (nums[0] > target) {
            while (l <= r) {
                int mid = (r - l) / 2 + l;
                if (nums[mid] < nums[0]) {
                    if (nums[mid] < target) {
                        l = mid + 1;
                    } else if (nums[mid] > target) {
                        r = mid - 1;
                    } else {
                        return mid;
                    }
                } else {
                    l = mid + 1;
                }
            }
        } else if (nums[0] < target) {
            while (l <= r) {
                int mid = (r - l) / 2 + l;
                if (nums[mid] < nums[0]) {
                    r = mid - 1;
                } else {
                    if (nums[mid] < target) {
                        l = mid + 1;
                    } else if (nums[mid] > target) {
                        r = mid - 1;
                    } else {
                        return mid;
                    }
                }
            }
        } else {
            return 0;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,1,2,3};
        T_33_SearchRotateArr searchRotateArr = new T_33_SearchRotateArr();
        int search = searchRotateArr.search(nums, 0);
        System.out.println(search);
    }
}
