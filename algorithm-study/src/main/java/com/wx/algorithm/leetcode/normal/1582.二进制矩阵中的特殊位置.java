package com.wx.algorithm.leetcode.normal;

/*
 * @Author: wangxiang wangxiang@flashhold.com
 * @Date: 2022-09-05 10:23:21
 * @LastEditors: wangxiang wangxiang@flashhold.com
 * @LastEditTime: 2022-09-05 10:30:54
 * @FilePath: \algorithm-study\src\main\java\com\wx\algorithm\leetcode\normal\1582.二进制矩阵中的特殊位置.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
/*
 * @lc app=leetcode.cn id=1582 lang=java
 *
 * [1582] 二进制矩阵中的特殊位置
 */

// @lc code=start
class Solution {
    public int numSpecial(int[][] mat) {
        int count = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                count += isSpecial(mat, i, j) ? 1 : 0;
            }
        }
        return count;
    }

    boolean isSpecial(int[][] mat, int x, int y) {
        if (mat[x][y] != 1) {
            return false;
        }

        for (int i = 0; i < y; i++) {
            if (mat[x][i] != 0) {
                return false;
            }
        }
        for (int i = y + 1; i < mat[0].length; i++) {
            if (mat[x][i] != 0) {
                return false;
            }
        }
        for (int j = 0; j < x; j++) {
            if (mat[j][y] != 0) {
                return false;
            }
        }
        for (int j = x + 1; j < mat.length; j++) {
            if (mat[j][y] != 0) {
                return false;
            }
        }

        return true;
    }
}
// @lc code=end
