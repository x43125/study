package com.wx.algorithm.leetcode.normal;

public class T_91_Decode {

    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i-1) != '0') {
                dp[i] += dp[i - 1];
            }
            if (i > 1
                    && s.charAt(i - 2) != '0'
                    && (s.charAt(i - 2) == '1' 
                        || (s.charAt(i - 2) == '2' 
                            && s.charAt(i-1) <= '6'))) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        T_91_Decode t_91_Decode = new T_91_Decode();
        int res = t_91_Decode.numDecodings("12");
        // 1 1 2 3
        // 11 2 3
        // 11 23
        // 1 12 3
        // 1 1 23
        System.out.println("res = " + res);
    }
}
