package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_200_NumIslands {
    public int numIslands(char[][] grid) {
        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    num++;
                    dfs(grid, i, j);
                }
            }
        }

        return num;
    }

    private void dfs(char[][] grid, int r, int c) {
        if (!inArea(grid, r, c)) {
            return;
        }

        if (grid[r][c] != '1') {
            return;
        }

        grid[r][c] = 2;
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    private boolean inArea(char[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }

    public static void main(String[] args) {
        char[][] area = {{'1','1','1','1','0'},{'1','1','0','1','0'},
        {'1','1','0','0','0'},{'0','0','0','0','1'}};
        T_200_NumIslands numIslands = new T_200_NumIslands();
        int num = numIslands.numIslands(area);
        System.out.println(num);
    }

}
