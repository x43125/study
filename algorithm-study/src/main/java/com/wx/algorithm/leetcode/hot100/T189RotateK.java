package com.wx.algorithm.leetcode.hot100;

import java.util.Arrays;

/**
 * @author wangxiang
 * @date 2023/6/21 11:10
 * @description
 */
public class T189RotateK {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        int k = 3;
//        int k = 11;

        rotate2(nums, k);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /**
     * 解法1:空间复杂度 O(n)
     *
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int[] tempArray = Arrays.copyOf(nums, nums.length);

        for (int i = 0; i < nums.length; i++) {
            // 此位置放 i+k-1， 如果大于 n 则取余
            int index = (n - k + i) % n;
            nums[i] = tempArray[index];
        }
    }

    /**
     * optimize: 空间复杂度：O(1)
     * 经典做法：reverse：先旋转 [0-n)，再旋转 [0-k)，最后再旋转 [k-n)
     * 真的妙这个做法
     * @param nums
     * @param k
     */
    public static void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
