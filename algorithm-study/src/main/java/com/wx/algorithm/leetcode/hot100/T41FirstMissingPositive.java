package com.wx.algorithm.leetcode.hot100;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wangxiang
 * @date 2023/6/21 12:51
 * @description
 */
public class T41FirstMissingPositive {
    public static void main(String[] args) {
//        int[] nums = {1};
//        int[] nums = {-2};
//        int[] nums = {2};
//        int[] nums = {3,4,-1,1};
//        int[] nums = {1,2,0};
        int[] nums = {7, 8, 9, 11, 12};
        int firstMissingPositive = firstMissingPositive2(nums);
        System.out.println("firstMissingPositive = " + firstMissingPositive);
    }

    /**
     * optimize：map
     *
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return n + 1;
    }

    /**
     * 用转化的思想
     *
     * @param nums
     * @return
     */
    public static int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        for (int num : nums) {
            if (num > 0 && num <= n) {
                arr[num-1] = -1;
            }
        }

        for (int num : arr) {
            if (num != -1) {
                return num;
            }
        }

        // 如果全部符合则缺下一个
        return n + 1;
    }
}
