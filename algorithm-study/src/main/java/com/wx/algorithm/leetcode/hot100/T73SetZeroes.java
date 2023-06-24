package com.wx.algorithm.leetcode.hot100;

/**
 * @author wangxiang
 * @date 2023/6/24 22:08
 * @description
 */
public class T73SetZeroes {
    public static void main(String[] args) {
//        int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};
        int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroesOptimize(matrix);
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    /**
     * 记录每行，每列是否有0出现，有则当前行就为0
     *
     * @param matrix
     */
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = cols[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] || cols[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 优化上侧代码，使用第一行第一列来代替上侧代码的rows, cols
     * 然后单独用两个变量表示第一行、第一列是否有0
     *
     * @param matrix
     */
    public static void setZeroesOptimize(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean row0Has0 = false, col0Has0 = false;
        // 先用两个单独的变量来标记一行，一列是否有值
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                row0Has0 = true;
                break;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                col0Has0 = true;
                break;
            }
        }

        // 用第一行第一列来标记该行该列是否有0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = matrix[i][0] = 0;
                }
            }
        }

        // 再根据第一行第一列的值，来判断该行该列是否全赋值0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 最后再根据一开始的其他两个变量来更新第一行，第一列
        if (row0Has0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
        if (col0Has0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
