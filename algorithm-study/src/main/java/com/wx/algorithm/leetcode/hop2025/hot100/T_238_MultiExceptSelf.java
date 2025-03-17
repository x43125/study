package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.Arrays;

public class T_238_MultiExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[n - 1] = nums[n - 1];
        for (int i = n - 2; i > -1; i--) {
            res[i] = res[i + 1] * nums[i];
        }

        int preProd = 1;
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                res[i] = preProd;
            } else {
                res[i] = preProd * res[i + 1];
            }
            preProd *= nums[i];
        }

        return res;
    }

    public int[] productExceptSelf_2(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, 1);
        int lp = 1, rp = 1;
        int l = 0, r = nums.length - 1;
        while (l < nums.length && r >= 0) {
            res[l] *= lp;
            lp *= nums[l++];

            res[r] *= rp;
            rp *= nums[r--];
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };
        // int[] nums = {-1,1,0,-3,3};
        // int[] nums = { -1, 1 };
        T_238_MultiExceptSelf multiExceptSelf = new T_238_MultiExceptSelf();
        int[] prods = multiExceptSelf.productExceptSelf_2(nums);
        for (int p : prods) {
            System.out.print(p + " ");
        }
        System.out.println();
    }
}
