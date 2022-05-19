package com.wx.algorithm.hop;

/**
 * @Description:
 * @Author: wx
 * @Date: 2022/05/12 18:53
 */
public class ReturnN {
    public static void main(String[] args) {
        String str = "1234";
        int n = getN(str);
        System.out.println(n);
    }

    public static int getN(String str) {
        if (str.length() < 2) {
            return str.length();
        }

        int[] dp = new int[str.length() + 1];
        dp[0] = 1;
        dp[1] = 1;

        char lastC = str.charAt(0);
        for (int i = 2; i <= str.length(); i++) {
            char nowC = str.charAt(i - 1);
            if ((lastC == '1') || (lastC == '2' && nowC <= '6')) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
            lastC = nowC;
        }

        return dp[str.length()];
    }
}
