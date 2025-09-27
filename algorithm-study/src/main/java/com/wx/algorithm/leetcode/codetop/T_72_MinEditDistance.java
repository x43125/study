package com.wx.algorithm.leetcode.codetop;

/**
 * 字符串a变到b的最小变更次数
 * 变更方式：增加一个，减少一个，变更一个
 */
public class T_72_MinEditDistance {
    public int minDistance(String word1, String word2) {
        /*
         * 动态规划：二维动态规划：核心：从第0-i的word1变成0-j的word2的次数
         * 如果末尾字符相同的话，则dp[i][j] = dp[i-1][j-1] 不需要变更
         * 如果末尾字符不同的话，则dp[i][j] = min(dp[i][j-1] + 1 (-1), dp[i-1][j] + 1 (+1)，
         * dp[i-1][j-1] + 1 (变更1))
         */
        
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "", word2 = "asa";
        T_72_MinEditDistance minEditDistance = new T_72_MinEditDistance();
        int mindDistance = minEditDistance.minDistance(word1, word2);
        System.out.println(mindDistance);
    }
}
