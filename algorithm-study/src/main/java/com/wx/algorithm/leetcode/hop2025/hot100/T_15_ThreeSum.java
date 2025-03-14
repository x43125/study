package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T_15_ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int target = -nums[i]; // 找-nums[i]
            int k = n - 1;
            for (int j = i + 1; j < n - 1; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                while (j < k && nums[j] + nums[k] > target) {
                    k--;
                }

                if (j == k)
                    break;
                if (nums[j] + nums[k] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    result.add(list);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = { -1, 0, 1, 2, -1, -4 };
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
