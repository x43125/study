package com.wx.algorithm.leetcode.hot100;

/**
 * @author wangxiang
 * @date 2023/6/21 11:45
 * @description
 */
public class T238ProductExceptSelf {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] products = productExceptSelf(nums);
        for (int product : products) {
            System.out.print(product + " ");
        }
        System.out.println();
    }

    /**
     * 不允许使用除法 & 时间复杂度 O(n)
     * 前缀和的转化使用方式：前缀积 & 倒置前缀积
     *
     * @param nums
     * @return
     */
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] preProducts = new int[n];
        int[] afterProducts = new int[n];
        int[] ans = new int[n];

        int preProduct = 1, afterProduct = 1;
        for (int i = 0; i < n; i++) {
            preProduct *= nums[i];
            preProducts[i] = preProduct;

            afterProduct *= nums[n - i - 1];
            afterProducts[n - i - 1] = afterProduct;
        }

        for (int i = 0; i < n; i++) {
            ans[i] = (i == 0 ? 1 : preProducts[i - 1]) * ((i == n - 1) ? 1 : afterProducts[i + 1]);
        }

        return ans;
    }
}
