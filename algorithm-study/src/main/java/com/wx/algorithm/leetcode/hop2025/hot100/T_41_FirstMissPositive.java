package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_41_FirstMissPositive {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int m = n + 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = m;
            }
        }

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num <= 0 && num > -m) {
                if (nums[-num - 1] > 0) {
                    nums[-num - 1] = -nums[-num - 1];
                }
            } else if (num > 0 && num < m) {
                if (nums[num - 1] > 0) {
                    nums[num - 1] = -nums[num - 1];
                }
            }
        }

        int i = 0;
        while (i < n && nums[i] <= 0) {
            i++;
        }

        return i + 1;
    }

    public static void main(String[] args) {
        // int[] nums = { 3, 4, -1, 1 };
        int[] nums = { 3, 4, -1, 1, 2, 9, 8, 2, 0, 5 };
        T_41_FirstMissPositive firstMissPositive = new T_41_FirstMissPositive();
        int firstMissingPositive = firstMissPositive.firstMissingPositive(nums);
        System.out.println(firstMissingPositive);
    }
}
