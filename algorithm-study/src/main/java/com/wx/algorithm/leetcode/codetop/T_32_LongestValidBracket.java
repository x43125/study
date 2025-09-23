package com.wx.algorithm.leetcode.codetop;

public class T_32_LongestValidBracket {
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (i-dp[i-1]-1 >= 0 && s.charAt(i-dp[i-1]-1) == '(') {
                    // 状态转移方程：dp[i] = 左右2位 + 前一位的有效括号长度 + 左括号左相邻的最大有效括号长度
                    dp[i] = 2 + dp[i-1] + (i-dp[i-1]-2 >= 0 ? dp[i-dp[i-1]-2] : 0);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        String s = "()(()(()";
        T_32_LongestValidBracket longestValidBracket = new T_32_LongestValidBracket();
        int i = longestValidBracket.longestValidParentheses(s);
        System.out.println(i);
    }
}
