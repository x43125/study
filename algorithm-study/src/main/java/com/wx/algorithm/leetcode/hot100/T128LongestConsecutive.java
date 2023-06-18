package com.wx.algorithm.leetcode.hot100;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wangxiang
 * @date 2023/6/13 23:34
 * @description
 */
public class T128LongestConsecutive {
    public static void main(String[] args) {
//        int[] nums = {0,3,7,2,5,8,4,6,0,1};
//        int[] nums = {1, 2, 0, 1};
//        int[] nums = {};
        int[] nums = {1};
        int i = longestConsecutiveOptimize(nums);
        System.out.println(i);
    }

    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int max = 0;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                continue;
            }
            if (nums[i - 1] + 1 != nums[i]) {
                max = Math.max(max, count);
                count = 0;
            }
            count++;
        }

        return Math.max(max, count);
    }

    public static int longestConsecutiveOptimize(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (Integer num : nums) {
            set.add(num);
        }
        int max = 0, count;
        for (int num : nums) {
            if (set.contains(num - 1)) {
                continue;
            }
            count = 1;
            while (set.contains(num + 1)) {
                num++;
                count++;
            }
            max = Math.max(max, count);
        }

        return max;
    }
}
