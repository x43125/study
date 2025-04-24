package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.Stack;

public class T_84_MaxArea {
    // "在一维数组中找第一个满足某种条件的数" 典型的单调栈
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        Stack<Integer> leftS = new Stack<>();
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            while (!leftS.isEmpty() && heights[i] < heights[leftS.peek()]) {
                leftS.pop();
            }
            left[i] = leftS.isEmpty() ? -1 : leftS.peek();
            leftS.add(i);
        }

        int[] right = new int[n];
        Stack<Integer> rightS = new Stack<>();
        for (int i = n-1; i >= 0; i--) {
            while (!rightS.isEmpty() && heights[i] < heights[rightS.peek()]) {
                rightS.pop();
            }
            right[i] = rightS.isEmpty() ? n : rightS.peek();
            rightS.add(i);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2,1,5,6,2,3};
        T_84_MaxArea maxArea = new T_84_MaxArea();
        int largestArea = maxArea.largestRectangleArea(nums);
        System.out.println(largestArea);

    }
}
