package com.wx.algorithm.leetcode.codetop;

import java.util.ArrayList;
import java.util.List;

public class T_54_SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int up = 0, down = m - 1, left = 0, right = n - 1;
        List<Integer> ans = new ArrayList<>();
        while (true) {
            for (int i = left; i <= right; i++) {
                ans.add(matrix[up][i]);
            }
            if (++up > down) {
                break;
            }

            for (int i = up; i <= down; i++) {
                ans.add(matrix[i][right]);
            }
            if (--right < left) {
                break;
            }

            for (int i = right; i >= left; i--) {
                ans.add(matrix[down][i]);
            }
            if (--down < up) {
                break;
            }

            for (int i = down; i >= up; i--) {
                ans.add(matrix[i][left]);
            }
            if (++left > right) {
                break;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        // int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{1,2,3,4},{5,6,7,8}};
        T_54_SpiralOrder t_54_SpiralOrder = new T_54_SpiralOrder();
        List<Integer> spiralOrder = t_54_SpiralOrder.spiralOrder(matrix);
        spiralOrder.forEach(o -> System.out.print(o + " "));
    }
}
