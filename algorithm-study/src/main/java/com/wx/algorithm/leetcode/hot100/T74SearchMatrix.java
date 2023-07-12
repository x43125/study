package com.wx.algorithm.leetcode.hot100;

/**
 * @author Shawn
 * @date 2023/7/12 09:07
 * @description
 */
public class T74SearchMatrix {

    public static void main(String[] args) {
//        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int[][] matrix = {{1}};
        int target = 0;
        T74SearchMatrix searchMatrix = new T74SearchMatrix();
        boolean searched = searchMatrix.searchMatrix(matrix, target);
        System.out.println("searched = " + searched);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length - 1, n = matrix[0].length - 1;

        // 先二分查找第一列，找到会在哪一行
        int start = 0, end = m;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (matrix[mid][0] < target) {
                start = mid + 1;
            } else if (matrix[mid][0] > target) {
                end = mid - 1;
            } else {
                return true;
            }
        }

        if (end < 0) {
            return false;
        }

        int index = end;
        start = 0;
        end = n;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (matrix[index][mid] < target) {
                start = mid + 1;
            } else if (matrix[index][mid] > target) {
                end = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
