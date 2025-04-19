package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_200_NumIslands {
    public int numIslands(char[][] grid) {
        int num = 0;

        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    num++;
                    dfs(grid, i, j);
                }
            }
        }

        return num;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (notInArea(grid, i, j)) {
            return;
        }

        if (grid[i][j] == '1') {
            grid[i][j] = '2';
        }

        dfs(grid, i-1, j);
        dfs(grid, i+1, j);
        dfs(grid, i, j-1);
        dfs(grid, i, j+1);
    }

    private boolean notInArea(char[][] grid, int i, int j) {
        return i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1';
    }

    public static void main(String[] args) {
        char[][] area = {{'1','1','1','1','0'},{'1','1','0','1','0'},
        {'1','1','0','0','0'},{'0','0','0','0','1'}};
        T_200_NumIslands numIslands = new T_200_NumIslands();
        int num = numIslands.numIslands(area);
        System.out.println(num);
    }

}
