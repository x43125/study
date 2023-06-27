package com.wx.algorithm.leetcode.hot100;

/**
 * @author wangxiang
 * @date 2023/6/27 10:13
 * @description
 */
public class T240SearchSpecialMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}};
        boolean b = searchMatrix(matrix, 2);
        System.out.println("b = " + b);
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length - 1, j = 0, rowLength = matrix[0].length;

        while (i >= 0 && j < rowLength) {
            if (matrix[i][j] < target) {
                j++;
            } else if (matrix[i][j] > target) {
                i--;
            } else {
                return true;
            }
        }

        return false;
    }
}
