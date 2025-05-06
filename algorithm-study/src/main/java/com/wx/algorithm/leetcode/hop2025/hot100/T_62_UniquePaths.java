package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.Arrays;

public class T_62_UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int k = 0; k < m; k++) {
            dp[k][0] = 1;
        }
        for (int k = 0; k < n; k++) {
            dp[0][k] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    public int uniquePaths_02(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // dp[j]是上方
                // dp[j-1]是左侧
                dp[j] += dp[j-1];
            }
        }

        return dp[n-1];
    }

    public static void main(String[] args) {
        int m = 3, n = 7;
        T_62_UniquePaths uniquePaths = new T_62_UniquePaths();
        int uniques = uniquePaths.uniquePaths_02(m, n);
        System.out.println(uniques);
    }
}
