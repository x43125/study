package com.wx.algorithm.leetcode.hot100;

/**
 * @author wangxiang
 * @date 2023/6/17 09:21
 * @description
 */
public class T11MaxArea {

    public static void main(String[] args) {
//        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] height = {1, 1};
        int maxArea = maxArea(height);
        System.out.println("maxArea = " + maxArea);
    }

    /**
     * 双指针，需要考虑长和宽的情况
     * 使用头尾指针，向中间逼近
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]) :
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }
}
