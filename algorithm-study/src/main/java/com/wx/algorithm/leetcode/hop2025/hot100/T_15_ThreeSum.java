package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T_15_ThreeSum {

    // -4 -1 -1 0 1 2
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 排序
        Arrays.sort(nums);
        int n = nums.length;
        // 遍历
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                // 如果
                continue;
            }
            // 转变为找剩下的两数之和
            int target = -nums[i];
            int j = i + 1, k = n - 1;
            while (j < k) {
                int temp = nums[j] + nums[k];
                if (temp == target) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // 看下一组是否符合
                    j++;
                    k--;
                    // 跳过重复的情况
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                } else if (temp < target) {
                    // 左指针右移
                    j++;
                } else {
                    // 右指针左移
                    k--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // int[] nums = { -1, 0, 1, 2, -1, -4 };
        int[] nums = { 0, 0, 0, 0, 0 };
        T_15_ThreeSum t_15_ThreeSum = new T_15_ThreeSum();
        List<List<Integer>> result = t_15_ThreeSum.threeSum(nums);
        for (List<Integer> list : result) {
            for (Integer item : list) {
                System.out.print(item + " ");
            }
            System.out.println();
        }

    }
}
