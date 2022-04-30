package com.wx.algorithm.leetcode.contest.weekend.w0430;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/04/30
 */
public class FindMissedGrid {
    public static void main(String[] args) {
//        int m = 4, n = 6;
//        int[][] guards = {{0, 0}, {1, 1}, {2, 3}}, walls = {{0, 1}, {2, 2}, {1, 4}};
        int m = 1, n = 100000;
        int[][] guards = {{0, 0}}, walls = {{0, 1}};
        System.out.println(countUnguarded(m, n, guards, walls));
    }

    /**
     * 优化
     * @param m
     * @param n
     * @param guards
     * @param walls
     * @return
     */
    public static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grids = init(m, n, guards, walls);

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grids[i][j] != 0) {
                    continue;
                }

                boolean hasPolice = false;
                for (int k = j - 1; k >= 0; k--) {
                    if (grids[i][k] == 1) {
                        hasPolice = true;
                        break;
                    } else if (grids[i][k] == 2) {
                        break;
                    }
                }
                if (hasPolice) {
                    continue;
                }
                for (int k = j + 1; k < n; k++) {
                    if (grids[i][k] == 1) {
                        hasPolice = true;
                        break;
                    } else if (grids[i][k] == 2) {
                        break;
                    }
                }
                if (hasPolice) {
                    continue;
                }
                for (int k = i - 1; k >= 0; k--) {
                    if (grids[k][j] == 1) {
                        hasPolice = true;
                        break;
                    } else if (grids[k][j] == 2) {
                        break;
                    }
                }
                if (hasPolice) {
                    continue;
                }
                for (int k = i + 1; k < m; k++) {
                    if (grids[k][j] == 1) {
                        hasPolice = true;
                        break;
                    } else if (grids[k][j] == 2) {
                        break;
                    }
                }
                if (hasPolice) {
                    continue;
                }
                res++;
            }
        }
        return res;
    }

    private static int[][] init(int m, int n, int[][] guards, int[][] walls) {
        int[][] grids = new int[m][n];
        for (int[] guard : guards) {
            grids[guard[0]][guard[1]] = 1;
        }

        for (int[] wall : walls) {
            grids[wall[0]][wall[1]] = 2;
        }

        return grids;
    }
}
