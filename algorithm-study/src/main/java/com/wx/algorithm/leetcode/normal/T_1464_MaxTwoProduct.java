package com.wx.algorithm.leetcode.normal;

/**
 * @author wangxiang
 * @date 2022/8/26 10:26
 * @description
 */
public class T_1464_MaxTwoProduct {
    public static void main(String[] args) {
        int[] nums = {3,4,5,2};
        T_1464_MaxTwoProduct maxTwoProduct = new T_1464_MaxTwoProduct();
        int i = maxTwoProduct.maxProduct(nums);
        System.out.println("i = " + i);
    }

    public int maxProduct(int[] nums) {
        int max = -1, secMax = -1;
        for (int num : nums) {
            if (num >= max) {
                secMax = max;
                max = num;
            } else if (num > secMax) {
                secMax = num;
            }
        }

        return (max-1) * (secMax-1);
    }
}
