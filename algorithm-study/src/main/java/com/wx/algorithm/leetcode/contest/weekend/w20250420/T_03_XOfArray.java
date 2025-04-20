package com.wx.algorithm.leetcode.contest.weekend.w20250420;

import java.util.Random;

public class T_03_XOfArray {
    /**
     * 前缀积太大了，越界
     * @param nums
     * @param k
     * @return
     */
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        for (int i = 0; i < k; i++) {
            long count = 0;
            for (int j = 0; j < nums.length; j++) {
                for (int m = 0; m < nums.length - j; m++) {
                    int x = j;
                    int y = nums.length - m -1;
                    int mul = 1;
                    while (x <= y) {
                        mul *= nums[x];
                        if (mul % 3 == i) {
                            count++;
                            break;
                        }
                        x++;
                    }
                }
            }
            result[i] = count;
        }

        return result;
    }

    public static void main(String[] args) {
        // Random random = new Random();
        // int[] nums = new int[100000];
        // for (int i = 0; i < nums.length; i++) {
        //     int nextInt = random.nextInt(nums.length);
        //     nums[i] = nextInt <= 0 ? 1 : nextInt;
        // }
        int[] nums = { 1, 2, 3, 4, 5 };
        int k = 3;
        long startTime = System.currentTimeMillis();
        T_03_XOfArray xOfArray = new T_03_XOfArray();
        long[] resultArray = xOfArray.resultArray(nums, k);
        System.out.println(System.currentTimeMillis() - startTime);
        for (long res : resultArray) {
            System.out.println(res);
        }
    }
}
