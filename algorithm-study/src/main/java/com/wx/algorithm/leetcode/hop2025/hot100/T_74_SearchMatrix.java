package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_74_SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length-1, j = 0;
        while (i >= 0 && j < matrix[0].length) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }

        return false;        
    }

    public static void main(String[] args) {
        int[][] nums = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 3;
        T_74_SearchMatrix searchMatrix = new T_74_SearchMatrix();
        boolean exists = searchMatrix.searchMatrix(nums, target);
        System.out.println(exists);
    }
}
