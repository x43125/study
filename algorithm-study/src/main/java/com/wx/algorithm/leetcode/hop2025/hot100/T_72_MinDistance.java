package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_72_MinDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m+1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < n+1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < m + 1; i++) {
            char c1 = word1.charAt(i-1);
            for (int j = 1; j < n + 1; j++) {
                char c2 = word2.charAt(j-1);
                // 仔细，沉下心来，慢慢想，总结规律：
                // 基本上就是当前点位和前三个位置的比较，
                // 即：(i,j)与(i-1,j),(i,j-1),(i-1,j-1)的关系
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "horse", word2 = "";
        T_72_MinDistance minDistance = new T_72_MinDistance();
        int min = minDistance.minDistance(word1, word2);
        System.out.println(min);
    }
}
