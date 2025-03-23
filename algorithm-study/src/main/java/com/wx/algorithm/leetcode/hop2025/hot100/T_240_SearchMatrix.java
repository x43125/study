package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_240_SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix[0].length;
        int i = matrix.length - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                i--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 }, { 3, 6, 9, 16, 22 }, { 10, 13, 14, 17, 24 },
                { 18, 21, 23, 26, 30 } };
        T_240_SearchMatrix searchMatrix = new T_240_SearchMatrix();
        boolean find = searchMatrix.searchMatrix(matrix, 20);
        System.out.println(find);
    }
}
