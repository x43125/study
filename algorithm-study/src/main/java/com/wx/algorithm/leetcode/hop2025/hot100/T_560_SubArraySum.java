package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.HashMap;
import java.util.Map;

public class T_560_SubArraySum {

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> preMap = new HashMap<>();
        // 方便计算
        preMap.put(0, 1);
        int n = nums.length;
        int count = 0, pre = 0;
        for (int i = 0; i < n; i++) {
            // 计算当前i的前缀和
            pre += nums[i];
            if (preMap.containsKey(pre - k)) {
                // 妙：如果包含，则说明有前缀可以得到目标值
                count += preMap.get(pre - k);
            }
            // 将当前前缀和插入map中
            preMap.put(pre, preMap.getOrDefault(pre, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        // int[] nums = { 0, 0, 0 };
        int[] nums = {1,2,3,2,1,1,1};
        // int[] nums = {1,-1,0};
        // int[] nums = {1,2,3};
        int k = 5;
        T_560_SubArraySum subArraySum = new T_560_SubArraySum();
        int count = subArraySum.subarraySum(nums, k);
        System.out.println(count);
    }
}
