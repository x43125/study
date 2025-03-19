package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.HashSet;
import java.util.Set;

public class T_73_ArraySetZeroes {
    public void setZeroes_02(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean flagRow0 = false;
        boolean flagCol0 = false;
        // 初始化00
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
                break;
            }
        }

        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                flagRow0 = true;
                break;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 不能从0开始，否则会直接把第一行第一列全改成0，然后把剩下的的也都改成了0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (flagCol0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }

        if (flagRow0) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
    }

    // 暴力法
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Set<Integer> row = new HashSet<>();
        Set<Integer> col = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    col.add(j);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row.contains(i) || col.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        // int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};
        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        T_73_ArraySetZeroes arraySetZeroes = new T_73_ArraySetZeroes();
        arraySetZeroes.setZeroes_02(matrix);
        for (int[] m : matrix) {
            for (int n : m) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
}
