package com.wx.algorithm.leetcode.normal;

public class T_59_SpiralMatrix_2 {
    public int[][] generateMatrix(int n) {
        int line = n;
        int row = n;
        int left = 0;
        int right = line - 1;
        int up = 0;
        int down = row - 1;
        int[][] matrix = new int[n][n];

        int num = 0;
        while (num < n*n) {
            if (up <= down) {
                // 在顶部从左向右遍历
                for (int j = left; j <= right; j++) {
                    matrix[up][j] = ++num;
                }
                // 上边界下移
                up++;
            }

            if (left <= right) {
                // 在右侧从上向下遍历
                for (int i = up; i <= down; i++) {
                    matrix[i][right] = ++num;
                }
                // 右边界左移
                right--;
            }

            if (up <= down) {
                // 在底部从右向左遍历
                for (int j = right; j >= left; j--) {
                    matrix[down][j] = ++num;
                }
                // 下边界上移
                down--;
            }

            if (left <= right) {
                // 在左侧从下向上遍历
                for (int i = down; i >= up; i--) {
                    matrix[i][left] = ++num;
                }
                // 左边界右移
                left++;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        T_59_SpiralMatrix_2 spiralMatrix_2 = new T_59_SpiralMatrix_2();
        int[][] generateMatrix = spiralMatrix_2.generateMatrix(5);
        for (int[] matrix : generateMatrix) {
            for (int no : matrix) {
                System.out.print(no + " ");
            }
            System.out.println();
        }

    }

}