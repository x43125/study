package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_42_Trap {

    // 计算每一个下标能储存的最高的水量
    // 每一个下标能储存的最高的水量 = Math.min(它及左侧最高的柱子, 它及右侧最高的柱子) - 它自身的高度
    public int trap_01(int[] height) {
        int n = height.length;

        // 初始化left
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // 初始化right
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i > -1; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // 计算
        int area = 0;
        for (int i = 0; i < height.length; i++) {
            area += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return area;
    }

    /**
     * 优化
     * 
     * @param height
     * @return
     */
    public int trap_02(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1;
        int leftMax = height[0], rightMax = height[right];
        int area = 0;

        while (left < right) {
            if (leftMax < rightMax) {
                area += leftMax - height[left];
                left++;
                leftMax = Math.max(leftMax, height[left]);
            } else {
                area += rightMax - height[right];
                right--;
                rightMax = Math.max(rightMax, height[right]);
            }
        }
        return area;
    }

    public static void main(String[] args) {
        int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        T_42_Trap t_42_Trap = new T_42_Trap();
        int area = t_42_Trap.trap_02(height);
        System.out.println(area);
    }
}
