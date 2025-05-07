package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_1143_LongestCommonSubSequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        // 设置成m+1,n+1 方便表示0
        int[][] dp = new int[m + 1][n + 1];
        // 从1开始，因为0的时候全为0
        for (int i = 1; i < m + 1; i++) {
            char c1 = text1.charAt(i - 1);
            // 从1开始，因为0的时候全为0
            for (int j = 1; j < n + 1; j++) {
                char c2 = text2.charAt(j - 1);
                // 如果当前两个值相等，则为 i-1,j-1的最大公共子序列+1
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 不同的话，则为text1去掉一个值或text2去掉一个值后两个的比例
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String text1 = "abcdbab", text2 = "bdcaba";
        T_1143_LongestCommonSubSequence longestCommonSubSequence = new T_1143_LongestCommonSubSequence();
        int longest = longestCommonSubSequence.longestCommonSubsequence(text1, text2);
        System.out.println(longest);
    }
}
