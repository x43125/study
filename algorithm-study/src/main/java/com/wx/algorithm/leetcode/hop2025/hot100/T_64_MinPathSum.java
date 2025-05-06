package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_64_MinPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        // int[][] grid = {{1,3,1}, {1,5,1}, {4,2,1}};
        // int[][] grid = {{1,2,3}, {4,5,6,}};
        int[][] grid = {{1}};
        T_64_MinPathSum minPathSum = new T_64_MinPathSum();
        int minPath = minPathSum.minPathSum(grid);
        System.out.println(minPath);
    }
}
