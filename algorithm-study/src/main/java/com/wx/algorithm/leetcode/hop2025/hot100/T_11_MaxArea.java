package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_11_MaxArea {

    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int result = 0;
        while (i < j) {
            int area = (j - i) * Math.min(height[i], height[j]);
            result = Math.max(area, result);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,8,6,2,5,4,8,3,7};
        T_11_MaxArea maxArea = new T_11_MaxArea();
        int area = maxArea.maxArea(nums);
        System.out.println(area);
    }
}
