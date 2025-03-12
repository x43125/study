package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.HashMap;
import java.util.Map;

public class T_1_TwoSum {
    public static void main(String[] args) {
        int[] nums = {2,11,15,7};
        int target = 11;
        T_1_TwoSum t_1_TwoSum = new T_1_TwoSum();
        int[] res = t_1_TwoSum.twoSum(nums, target);
        for (int i : res) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }

        return null;
    }
}
