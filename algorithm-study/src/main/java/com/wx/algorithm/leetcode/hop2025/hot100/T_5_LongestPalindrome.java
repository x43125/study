package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_5_LongestPalindrome {
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                // 核心
                if (charArray[i] != charArray[j]) {
                    // 如果两头的值不相同，则直接false
                    dp[i][j] = false;
                } else {
                    // 如果相同的话：
                    if (j - i < 3) {
                        // 如果两个值相距小于3，即2或1或0，则可直接判断为true，
                        dp[i][j] = true;
                    } else {
                        // 否则要看内部的字符串是不是回文串，也就是i+1,j-1
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public static void main(String[] args) {
        String s = "acbbab";
        T_5_LongestPalindrome longestPalindrome = new T_5_LongestPalindrome();
        String longestChild = longestPalindrome.longestPalindrome(s);
        System.out.println(longestChild);
    }
}
