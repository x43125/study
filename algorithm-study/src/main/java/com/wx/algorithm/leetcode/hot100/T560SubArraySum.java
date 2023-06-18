package com.wx.algorithm.leetcode.hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxiang
 * @date 2023/6/18 22:18
 * @description
 */
public class T560SubArraySum {
    public static void main(String[] args) {
//        int[] nums = {1,1,1};
        int[] nums = {1,2,3};
        int k = 3;
//        int count = subarraySum(nums, k);
        int count = subarraySum2(nums, k);
        System.out.println("count = " + count);
    }

    /**
     * 暴力法
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 前缀和
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum2(int[] nums, int k) {
        int preSum = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            preSum += num;
            if (map.containsKey(preSum - k)) {
                count += map.get(preSum - k);
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }

        return count;
    }
}
