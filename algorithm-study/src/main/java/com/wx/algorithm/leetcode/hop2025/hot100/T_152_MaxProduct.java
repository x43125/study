package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_152_MaxProduct {

    /**
     * 转移方程:
     * fmax(n) = Max(fmax(n-1) * num, fmin(n-1) * num)
     * fmin(n) = Min(fmax(n-1) * num, fmin(n-1) * num)
     * 
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        long max = 1l, min = 1l;
        int ans = nums[0];
        for (int num : nums) {
            long tempMax = max;
            long tempMin = min;
            max = Math.max(num, Math.max(tempMax * num, tempMin * num));
            min = Math.min(num, Math.min(tempMax * num, tempMin * num));
            ans = Math.max(ans, (int) max);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {  -2 };
        T_152_MaxProduct maxProduct = new T_152_MaxProduct();
        int max = maxProduct.maxProduct(nums);
        System.out.println(max);
    }
}
