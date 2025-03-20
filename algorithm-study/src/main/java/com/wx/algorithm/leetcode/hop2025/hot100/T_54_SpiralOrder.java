package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.List;

public class T_54_SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int upr = n-1, rightDown = m-1, downLeft = 0, leftUp = 1;

        int i = 0, j = -1;
        List<Integer> result = new ArrayList<>();
        while (true) {
            // 右 下 左 上
            j++;
            if (j > upr) {
                break;
            }
            while (j <= upr) {
                result.add(matrix[i][j]);
                j++;
            }
            upr--;
            j--;
            i++;
            if (i > rightDown) {
                break;
            }
            while (i <= rightDown) {
                result.add(matrix[i][j]);
                i++;
            }
            rightDown--;
            i--;
            j--;
            if (j < downLeft) {
                break;
            }
            while (j >= downLeft) {
                result.add(matrix[i][j]);
                j--;
            }
            downLeft++;
            j++;
            i--;
            if (i < leftUp) {
                break;
            }
            while (i >= leftUp) {
                result.add(matrix[i][j]);
                i--;
            }
            leftUp++;
            i++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        // int[][] matrix = {{1},{2}};
        T_54_SpiralOrder spiralOrder = new T_54_SpiralOrder();
        List<Integer> res = spiralOrder.spiralOrder(matrix);
        for (Integer r : res) {
            System.out.print(r + " ");
        }
        System.out.println();
    }
}
