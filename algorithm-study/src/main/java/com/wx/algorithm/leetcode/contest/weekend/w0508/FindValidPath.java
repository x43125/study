package com.wx.algorithm.leetcode.contest.weekend.w0508;

/**
 * @Descrption: grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
 * true
 * @Author: x43125
 * @Date: 22/05/08
 */
public class FindValidPath {
    public static boolean hasValidPath(char[][] grid) {
        boolean[][][] dp = new boolean[grid.length + 1][grid[0].length + 1][103];
        dp[0][0][1] = true;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                for (int k = 1; k <= 101; k++) {
                    int direction = grid[i][j] == '(' ? -1 : 1;
                    dp[i][j + 1][k] |= dp[i][j][k + direction];
                    dp[i + 1][j][k] |= dp[i][j][k + direction];
                }
            }
        }

        return dp[grid.length - 1][grid[0].length][1];
    }

    public static void main(String[] args) {
//        char[][] grid = {{'(','(','('},{')','(',')'},{'(','(',')'},{'(','(',')'}};
        char[][] grid = {{')', ')'}, {'(', '('}};
        boolean b = hasValidPath(grid);
        System.out.println(b);
    }
}

/*
        boolean[][][] dp = new boolean[grid.length + 1][grid[0].length + 1][103];
		dp[0][0][1] = true;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				for (int k = 1; k <= 101; k++) {
					dp[i][j + 1][k] |= dp[i][j][k + (grid[i][j] == '(' ? -1 : 1)];
					dp[i + 1][j][k] |= dp[i][j][k + (grid[i][j] == '(' ? -1 : 1)];
				}
			}
		}
		return dp[grid.length - 1][grid[0].length][1];
 */
/*
        bool hasValidPath(vector<vector<char>>& s) {
        vector<vector<vector<int> > > f;
        int n = s.size();
        int m = s[0].size();
        f.resize(n + 1);
        for (int i = 0; i <= n; i++)
        {
            f[i].resize(m + 1);
            for (int j = 0; j <= m; j++)
            {
                f[i][j].resize(n + m);
            }
        }
        f[1][0][0] = true;
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= m; j++)
            {
                if (s[i - 1][j - 1] == '(')
                {
                    for (int k = 1; k < n + m; k++)
                    {
                        f[i][j][k] |= f[i - 1][j][k - 1];
                        f[i][j][k] |= f[i][j - 1][k - 1];
                    }
                }
                else // ')'
                {
                    for (int k = 0; k + 1 < n + m; k++)
                    {
                        f[i][j][k] |= f[i - 1][j][k + 1];
                        f[i][j][k] |= f[i][j - 1][k + 1];
                    }
                }
            }
        }
        return f[n][m][0];
    }
 */