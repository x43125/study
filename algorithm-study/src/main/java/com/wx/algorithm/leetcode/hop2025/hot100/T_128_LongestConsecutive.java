package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.HashSet;
import java.util.Set;

public class T_128_LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = -1;
        for (int num : nums) {
            // 不包含num-1的时候，说明num就是可能的序列的最小值
            if (!set.contains(num - 1)) {
                int curN = num;
                int curS = 1;
                // 朝上找
                while (set.contains(curN + 1)) {
                    curN++;
                    curS++;
                }
                longest = Math.max(longest, curS);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        T_128_LongestConsecutive longestConsecutive = new T_128_LongestConsecutive();
        int[] nums = { 100, 4, 200, 1, 3, 2, 300, 5, 600 };
        int result = longestConsecutive.longestConsecutive(nums);
        System.out.println(result);
    }
}
