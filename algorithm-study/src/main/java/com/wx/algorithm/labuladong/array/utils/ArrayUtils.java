package com.wx.algorithm.labuladong.array.utils;

public class ArrayUtils {
    public static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }    
}
