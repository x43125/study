package com.wx.algorithm.leetcode.hot100;

/**
 * @author Shawn
 * @date 2023/11/5 21:26
 * @description
 */
public class T33SearchInRotatedArray {
    public static void main(String[] args) {
        int[] arr = {4,5,6,7,1,2,3};
        T33SearchInRotatedArray search = new T33SearchInRotatedArray();
        int ans = search.search(arr, 4);
        System.out.println("ans = " + ans);
    }

    public int search(int[] nums, int target) {
        if (nums[0] <= target) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < target) {
                    continue;
                }
                return nums[i] == target ? i : -1;
            }
        } else {
            for (int i = nums.length - 1; i >= 0; i--) {
                if (nums[i] > target) {
                    continue;
                }
                return nums[i] == target ? i : -1;
            }
        }

        return -1;
    }
}
