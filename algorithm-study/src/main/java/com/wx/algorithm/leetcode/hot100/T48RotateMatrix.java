package com.wx.algorithm.leetcode.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxiang
 * @date 2023/6/27 07:29
 * @description
 */
public class T48RotateMatrix {

    public static void main(String[] args) {
//        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        int[][] matrix = {{5}};
        rotate(matrix);
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int count = n / 2;
        for (int i = 0; i < count; i++) {
            int j = i;
            while (j < n - i - 1) {
                int temp = matrix[i][j];
                // 左 -》 上
                matrix[i][j] = matrix[n - j - 1][i];
                // 下 -》 左
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                // 右 -》 下
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                // 上 -》 右
                matrix[j][n - i - 1] = temp;
                j++;
            }
        }
    }
}
