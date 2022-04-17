package com.wx.algorithm.leetcode.normal;

public class T_48_RotateArray {
    public void rotate(int[][] matrix) {
        rotateWithLeftUpToRightDown(matrix);
        reverse(matrix);
    }

    private void rotateWithLeftUpToRightDown(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }



    private void reverse(int[][] matrix) {
        int length = matrix[0].length;
        int half = length / 2;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < half; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][length - j - 1];
                matrix[i][length - j - 1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 2, 3, 0 },
                { 4, 5, 6, 0 },
                { 7, 8, 9, 0 },
                { 10, 11, 12, 0 },
        };

        T_48_RotateArray rotateArray = new T_48_RotateArray();

        rotateArray.rotate(matrix);
        for (int[] is : matrix) {
            for (int is2 : is) {
                System.out.print(is2 + " ");
            }
            System.out.println();
        }

    }

}