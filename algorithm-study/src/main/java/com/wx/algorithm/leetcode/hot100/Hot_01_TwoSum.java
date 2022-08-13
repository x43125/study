package com.wx.algorithm.leetcode.hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/08/13
 */
public class Hot_01_TwoSum {
    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 4, 2};
        int target = 6;
        Hot_01_TwoSum twoSum = new Hot_01_TwoSum();
        int[] sum = twoSum.twoSum(arr, target);
        System.out.println(sum[0] + ":" + sum[1]);
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> index = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (index.containsKey(nums[i])) {
                res[0] = index.get(nums[i]);
                res[1] = i;
                return res;
            } else {
                index.put(target - nums[i], i);
            }
        }

        return res;
    }
}
