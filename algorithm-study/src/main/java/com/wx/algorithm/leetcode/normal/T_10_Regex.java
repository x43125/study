package com.wx.algorithm.leetcode.normal;

/**
 * [.]匹配一个字符
 * [*]匹配0或多个前面那个字符
 */
public class T_10_Regex {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // dp数组
        boolean[][] f = new boolean[m + 1][n + 1];
        // 初始化
        f[0][0] = true;
        // 遍历
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                // 状态转移
                // 
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    public static void main(String[] args) {
        String s = "aa", p = ".*";
        T_10_Regex regex = new T_10_Regex();
        boolean match = regex.isMatch(s, p);
        System.out.println(match);
    }
}
