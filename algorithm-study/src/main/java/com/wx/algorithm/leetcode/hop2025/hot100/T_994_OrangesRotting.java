package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.List;

public class T_994_OrangesRotting {

    private static int maxLevel;

    public int orangesRotting(int[][] grid) {
        maxLevel = 0;
        List<int[]> rots = new ArrayList<>();
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    rots.add(new int[] { i, j });
                }
            }
        }

        while (true) {
            boolean all = false;
            List<int[]> newRots = new ArrayList<>();
            for (int[] rot : rots) {
                boolean up = rotting(grid, rot[0] - 1, rot[1], newRots);
                boolean down = rotting(grid, rot[0] + 1, rot[1], newRots);
                boolean left = rotting(grid, rot[0], rot[1] - 1, newRots);
                boolean right = rotting(grid, rot[0], rot[1] + 1, newRots);
                // 至少有一个是true
                all = all || up || down || left || right;
            }
            rots = newRots;
            if (all) {
                maxLevel++;
            } else {
                break;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return maxLevel;
    }

    private boolean rotting(int[][] grid, int i, int j, List<int[]> newRots) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
            grid[i][j] = 2;
            newRots.add(new int[]{i, j});
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        // int[][] grid = { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
        // int[][] grid = { { 0, 2 } };
        int[][] grid = { { 2, 1, 1 }, { 1, 1, 1 }, { 0, 1, 2 } };
        T_994_OrangesRotting orangesRotting = new T_994_OrangesRotting();
        int mins = orangesRotting.orangesRotting(grid);
        if (mins == -1) {
            System.out.println("不可能完全腐烂");
        } else {
            System.out.println("完全腐烂需要：" + mins + "分钟");
        }
    }
}