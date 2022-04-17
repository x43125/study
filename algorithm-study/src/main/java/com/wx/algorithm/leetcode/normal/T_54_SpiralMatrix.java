package com.wx.algorithm.leetcode.normal;

import java.util.ArrayList;
import java.util.List;

public class T_54_SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = n - 1;
        int up = 0;
        int down = m - 1;

        List<Integer> res = new ArrayList<>();
        while (res.size() < m * n) {
            // 转一圈是一轮（右下左上）

            if (up <= down) {
                // 在顶部从左向右遍历
                for (int j = left; j <= right; j++) {
                    res.add(matrix[up][j]);
                }
                // 上边界下移
                up++;
            }

            if (left <= right) {
                // 在右侧从上向下遍历
                for (int i = up; i <= down; i++) {
                    res.add(matrix[i][right]);
                }
                // 右边界左移
                right--;
            }

            if (up <= down) {
                // 在底部从右向左遍历
                for (int j = right; j >= left; j--) {
                    res.add(matrix[down][j]);
                }
                // 下边界上移
                down--;
            }

            if (left <= right) {
                // 在左侧从下向上遍历
                for (int i = down; i >= up; i--) {
                    res.add(matrix[i][left]);
                }
                // 左边界右移
                left++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1,2,3,4,5},
            {1,2,3,4,5},
            {1,2,3,4,5},
            {1,2,3,4,5},
            {1,2,3,4,5}
        };
        T_54_SpiralMatrix spiralMatrix = new T_54_SpiralMatrix();
        List<Integer> spiralOrder = spiralMatrix.spiralOrder(matrix);
        for (int i = 0; i < spiralOrder.size(); i++) {
            if (i % 5 == 0) {
                System.out.println();
            }
            System.out.print(spiralOrder.get(i) + " ");
        }

    }

}