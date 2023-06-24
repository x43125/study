package com.wx.algorithm.leetcode.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxiang
 * @date 2023/6/24 22:44
 * @description
 */
public class T54SpiralOrder {

    public static void main(String[] args) {
//        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> ans = spiralOrder(matrix);
        ans.forEach(System.out::println);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        int up = 0, down = matrix.length, left = 0, right = matrix[0].length;
        int i = 0, j = 0;
        List<Integer> ans = new ArrayList<>();

        int count = 0;
        while (i >= up && i < down && j >= left && j < right) {
            // 右
            while (j < right) {
                ans.add(matrix[i][j++]);
            }
            count++;
            up++;
            j--;
            i++;
            while (i < down) {
                ans.add(matrix[i++][j]);
                count = 0;
            }
            count++;
            right--;
            i--;
            j--;
            if (count >= 2) {
                return ans;
            }
            while (j >= left) {
                ans.add(matrix[i][j--]);
                count = 0;
            }
            count++;
            down--;
            j++;
            i--;
            if (count >= 2) {
                return ans;
            }
            while (i >= up) {
                ans.add(matrix[i--][j]);
                count = 0;
            }
            count++;
            left++;
            i++;
            j++;
            if (count >= 2) {
                return ans;
            }
        }
        return ans;
    }
}
