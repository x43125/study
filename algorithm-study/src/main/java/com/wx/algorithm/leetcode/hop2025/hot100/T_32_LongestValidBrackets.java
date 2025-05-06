package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_32_LongestValidBrackets {
    public int longestValidParentheses(String s) {
        int max = 0;
        // dp表示，当前位置对应的有效括号长度
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            // 如果当前是)
            if (s.charAt(i) == ')') {
                // 如果前一个值是(，则dp[i] = (i>=2 ? dp[i-2] : 0) + 2;
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    // 如果前一个不是(，则判断，前一个dp的长度，且i-前一个dp的长度的位置是(
                    // 则 dp = dp[i-1] + ((i-dp[i-1]) >= 2 ? dp[i-dp[i-1] - 2] : 0) + 2
                    // dp[i-dp[i-1]-2] 代表：从i向前数 dp[i-1]个长度，再数2个长度的位置
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String s = ")(())(";
        T_32_LongestValidBrackets longestValidBrackets = new T_32_LongestValidBrackets();
        int longestValidParentheses = longestValidBrackets.longestValidParentheses(s);
        System.out.println(longestValidParentheses);
    }
}
