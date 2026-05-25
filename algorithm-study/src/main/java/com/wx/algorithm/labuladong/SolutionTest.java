package com.wx.algorithm.labuladong;

public class SolutionTest {
    public int countTexts(String pressedKeys) {
        // 经典的一道题
        // 问题：比如 2-abc，那如果连续出现 4 次 2，能表示a吗？也就是转了一圈回来了
        // 先不考虑这种情况，只考虑每次都是正中目标的情况
        // 拆分下来其实就是：每一段的可能的数量，不同段的可能性累乘 = sum
        // dp[i] = 当前字符串长度的字符串可能数
        // dp[i] = 1. 与上一个值相同 = 3位数字符 dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
        // = 4位数字符 dp[i] = dp[i-1] + dp[i-2] + dp[i-3] + dp[i-4]
        // = 2. 与上一个值不同 = dp[i-1]

        int mod = 1_000_000_007;
        long[] dp3 = { 1, 2, 4 };
        long[] dp4 = { 1, 2, 4, 8 };
        long ans = 1;
        char pre = pressedKeys.charAt(0);
        int cnt = 1;
        long dpNow = 1;
        for (int i = 1; i < pressedKeys.length(); i++) {
            char now = pressedKeys.charAt(i);
            if (now == pre) {
                cnt++;
                // 旧
                if (now == '7' || now == '9') {
                    // 4
                    if (cnt <= 4) {
                        dpNow = dp4[cnt-1];
                    } else {
                        dpNow = (dp4[0] + dp4[1] + dp4[2] + dp4[3]) % mod;
                        dp4[0] = dp4[1];
                        dp4[1] = dp4[2];
                        dp4[2] = dp4[3];
                        dp4[3] = dpNow;
                    }
                } else {
                    // 3
                    if (cnt <= 3) {
                        dpNow = dp3[cnt-1];
                    } else {
                        dpNow = (dp3[0] + dp3[1] + dp3[2]) % mod;
                        dp3[0] = dp3[1];
                        dp3[1] = dp3[2];
                        dp3[2] = dpNow;
                    }
                }
            } else {
                // 新
                ans = (ans * dpNow) % mod;
                dpNow=1;
                cnt = 1;
                dp3 = new long[] { 1, 2, 4 };
                dp4 = new long[] { 1, 2, 4, 8 };
            }
            pre = now;
        }
        if (cnt != 1) {
            ans = (ans * dpNow) % mod;
        }

        return (int)ans;
    }

    public static void main(String[] args) {
        int a = 1;
        Integer b = 1;
        Integer c = new Integer(1);
        Integer d = new Integer(1);

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(b == c);
        System.out.println(c == d);

        // SolutionTest solution = new SolutionTest();
        // long time = System.currentTimeMillis();
        // int ans = solution.countTexts("88888888888888888888888888888999999999999999999999999999994444444444444444444444444444488888888888888888888888888888555555555555555555555555555556666666666666666666666666666666666666666666666666666666666222222222222222222222222222226666666666666666666666666666699999999999999999999999999999888888888888888888888888888885555555555555555555555555555577777777777777777777777777777444444444444444444444444444444444444444444444444444444444433333333333333333333333333333555555555555555555555555555556666666666666666666666666666644444444444444444444444444444999999999999999999999999999996666666666666666666666666666655555555555555555555555555555444444444444444444444444444448888888888888888888888888888855555555555555555555555555555555555555555555555555555555555555555555555555555555555999999999999999555555555555555555555555555554444444444444444444444444444444555");
        // System.out.println("ans:" + ans);
        // System.out.println(System.currentTimeMillis() - time);
    }
}
