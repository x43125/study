package com.wx.algorithm.labuladong.array.prefixsum;

public class TwoDimensionalAreaSum {

    private int[][] prefixArr;

    public TwoDimensionalAreaSum(int[][] matrix) {
        this.prefixArr = new int[matrix.length][matrix[0].length];
        this.prefixArr[0][0] = matrix[0][0];
        for (int i = 1; i < matrix[0].length; i++) {
            prefixArr[0][i] = prefixArr[0][i - 1] + matrix[0][i];
        }
        for (int i = 1; i < matrix.length; i++) {
            prefixArr[i][0] = prefixArr[i - 1][0] + matrix[i][0];
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                prefixArr[i][j] = prefixArr[i - 1][j] + prefixArr[i][j - 1] - prefixArr[i - 1][j - 1] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (row1 == 0 && col1 == 0) {
            return prefixArr[row2][col2];
        } else if (row1 == 0) {
            return prefixArr[row2][col2] - prefixArr[row2][col1 - 1];
        } else if (col1 == 0) {
            return prefixArr[row2][col2] - prefixArr[row1 - 1][col2];
        } else {
            return prefixArr[row2][col2] - prefixArr[row1 - 1][col2] - prefixArr[row2][col1 - 1]
                    + prefixArr[row1 - 1][col1 - 1];
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 3, 0, 1, 4, 2 },
                { 5, 6, 3, 2, 1 },
                { 1, 2, 0, 1, 5 },
                { 4, 1, 0, 1, 7 },
                { 1, 0, 3, 0, 5 } };
        int row1 = 2;
        int col1 = 1;
        int row2 = 4;
        int col2 = 3;

        TwoDimensionalAreaSum areaSum = new TwoDimensionalAreaSum(matrix);
        int sumRegion = areaSum.sumRegion(row1, col1, row2, col2);
        System.out.println(sumRegion);

        for (int[] is : areaSum.prefixArr) {
            for (int is2 : is) {
                System.out.print(is2 + " ");
            }
            System.out.println();
        }

        /*
         * { 2, 1, 4, 3 },
         * { 1, 1, 2, 2 },
         * { 1, 2, 2, 4 }
         */

    }
}