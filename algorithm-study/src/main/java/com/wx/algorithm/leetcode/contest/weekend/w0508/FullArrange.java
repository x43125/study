package com.wx.algorithm.leetcode.contest.weekend.w0508;


import java.util.Arrays;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/08
 */
public class FullArrange {
    public static int countTexts(String pressedKeys) {
        int[] dp = new int[pressedKeys.length() + 1];
        dp[0] = 1;
        for (int i = 0; i < pressedKeys.length(); i++) {
            for (int j = i; j >= 0; j--) {
                if (pressedKeys.charAt(j) != pressedKeys.charAt(i)) {
                    break;
                }

                char now = pressedKeys.charAt(i);
                int nowLength = 3;
                if (now == '7' || now == '9') {
                    nowLength = 4;
                }
                if (j <= i - nowLength) {
                    break;
                }

                dp[i + 1] = (dp[i + 1] + dp[j]) % 1000000007;
            }
        }
        return dp[pressedKeys.length()];
    }

    public static void main(String[] args) {
        String pressedKeys = "222";
        int i = countTexts_self(pressedKeys);
        System.out.println(i);
    }

    public static int countTexts_02(String pressedKeys) {
        final int mod = 1000000007;
        long ans = 1;

        for (int i = 0, j = 0; i < pressedKeys.length(); i = j) {
            while (j < pressedKeys.length() && pressedKeys.charAt(i) == pressedKeys.charAt(j)) {
                j++;
            }

            int x = pressedKeys.charAt(i) - '0', count = j - i;
            int[] s = new int[count + 1];
            s[0] = 1;
            int num = 3;
            if (x == 7 || x == 9) num = 4;
            for (int k = 1; k <= count; k++) {
                for (int l = 1; l <= num && k - l >= 0; l++) {
                    s[k] = (s[k] + s[k - l]) % mod;
                }
            }

            ans = ans * s[count] % mod;
        }

        return (int) ans;
    }

    public static int countTexts_self(String pressedKeys) {
        // if s!=7 && s!=9  dp[i] = dp[i-1]+dp[i-2]+dp[i-3]
        // else dp[i] = dp[i-1]+dp[i-2]+dp[i-3]+dp[i-4]
        final int mod = 1000000007;
        long ans = 1;
        for (int i = 0, j = 0; i < pressedKeys.length(); i = j) {
            while (j < pressedKeys.length() && pressedKeys.charAt(i) == pressedKeys.charAt(j)) {
                j++;
            }
            int length = j - i;
            int c = pressedKeys.charAt(i) - '0';
            int count = 3;
            if (c == 7 || c == 9) {
                count = 4;
            }
            int[] s = new int[length + 1];
            s[0] = 1;
            for (int k = 1; k <= length; k++) {
                for (int l = 1; l <= count && l <= k; l++) {
                    s[k] = (s[k] + s[k - l]) % mod;
                }
            }
            ans = ans * s[length] % mod;
        }

        return (int) ans;
    }
}

/*
// the champion's answer
    int countTexts(string s) {
        const int p = 1000000007;
        int n = s.size();
        vector<int> f(n + 1);
        f[0] = 1;
        for (int i = 1; i <= n; i++)
        {
            f[i] = f[i - 1];
            if (i >= 2 && s[i - 1] == s[i - 2])
            {
                f[i] = (f[i] + f[i - 2]) % p;
            }
            if (i >= 3 && s[i - 1] == s[i - 2] && s[i - 2] == s[i - 3])
            {
                f[i] = (f[i] + f[i - 3]) % p;
            }
            if (i >= 4 && s[i - 1] == s[i - 2] && s[i - 2] == s[i - 3] && s[i - 3] == s[i - 4] && (s[i - 1] == '7' || s[i - 1] == '9'))
            {
                f[i] = (f[i] + f[i - 4]) % p;
            }
        }

        return f[n];
    }
 */

/*
// the second one's answer
    const int mod = 1e9 + 7;
    vector<int> c(10, 3);
    c[7] = c[9] = 4;
    long long ans = 1;
    for(int i = 0, j = 0; i < s.size(); i = j) {
        while(j < s.size() && s[i] == s[j]) ++j;
        int x = s[i] - '0', cnt = j - i;
        vector<long long> s(cnt + 1);
        s[0] = 1;
        for(int i = 1; i <= cnt; ++i) {
            for(int j = 1; j <= c[x]; ++j) {
                if(i - j >= 0) s[i] = (s[i] + s[i - j]) % mod;
            }
        }
        ans = ans * s[cnt] % mod;
    }
    ans %= mod;
    return ans;
 */

/*
// the only one java user's answer
int[] dp = new int[pressedKeys.length() + 1];
		dp[0] = 1;
		for (int i = 0; i < pressedKeys.length(); i++) {
			for (int j = i; j >= 0 && j > i - ("79".contains("" + pressedKeys.charAt(i)) ? 4 : 3)
					&& pressedKeys.charAt(j) == pressedKeys.charAt(i); j--) {
				dp[i + 1] = (dp[i + 1] + dp[j]) % 1000000007;
			}
		}
		return dp[pressedKeys.length()];
 */