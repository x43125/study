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
//        System.out.println(countUnguarded(m, n, guards, walls));
        System.out.println(countUnguarded_02(m, n, guards, walls));
    }

    /**
     * 优化 时间耗时太长了
     *
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

    /**
     * 从警卫的角度来看，标记能见查到的位置
     *
     * @param m
     * @param n
     * @param guards
     * @param walls
     * @return
     */
    public static int countUnguarded_02(int m, int n, int[][] guards, int[][] walls) {
        int[][] grids = init(m, n, guards, walls);
        int no = m * n - guards.length - walls.length;
        for (int[] guard : guards) {
            int x = guard[0], y = guard[1];
            for (int i = x - 1; i >= 0; i--) {
                if (grids[i][y] == 0) {
                    no--;
                    grids[i][y] = 3;
                } else if (grids[i][y] == 3) {
                } else {
                    break;
                }
            }
            for (int i = x + 1; i < m; i++) {
                if (grids[i][y] == 0) {
                    no--;
                    grids[i][y] = 3;
                } else if (grids[i][y] == 3) {
                } else {
                    break;
                }
            }
            for (int i = y - 1; i >= 0; i--) {
                if (grids[x][i] == 0) {
                    no--;
                    grids[x][i] = 3;
                } else if (grids[x][i] == 3) {
                } else {
                    break;
                }
            }
            for (int i = y + 1; i < n; i++) {
                if (grids[x][i] == 0) {
                    no--;
                    grids[x][i] = 3;
                } else if (grids[x][i] == 3) {
                } else {
                    break;
                }
            }
        }

        return no;
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
