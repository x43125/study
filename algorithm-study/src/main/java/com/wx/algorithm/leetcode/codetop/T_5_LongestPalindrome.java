package com.wx.algorithm.leetcode.codetop;

public class T_5_LongestPalindrome {
    public String longestPalindrome(String s) {
        // 是一个二维动态规划,动态规划表示的是，从i到j的字符串是否是回文字符串
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        // 对角线的是i==j，也就是单个字符，必然是回文
        for (int i=0; i<len; i++) {
            dp[i][i] = true;
        }
        
        int max = 1, start = 0, end = 0;
        for (int j=1; j<len; j++) {
            // 每行去一个个处理
            char c1 = s.charAt(j);
            for (int i=0; i<j; i++) {
                // 每行的每列
                char c2 = s.charAt(i);
                // 开头和结尾相同，才有可能是回文串
                if (c1 == c2) {
                    // 如果是2位或1位数则必是
                    if (j-i < 3) {
                        dp[i][j] = true;
                    } else {
                        // 否则就看除了两头的内部字符串是否是回文串
                        dp[i][j] = dp[i+1][j-1];
                    }
                } else {
                    // 两头都不一样，那内侧更不可能一样
                    dp[i][j] = false;
                }

                // 每次都更新最大值和最大值起点
                if (dp[i][j] && j-i+1 > max) {
                    max = j-i+1;
                    start = i;
                    end = j;
                }
            }
        }

        return s.substring(start, end+1);
    }

    public static void main(String[] args) {
        String s = "abccbaabc";
        T_5_LongestPalindrome longestPalindrome = new T_5_LongestPalindrome();
        String ans = longestPalindrome.longestPalindrome(s);
        System.out.println(ans);
    }
}
