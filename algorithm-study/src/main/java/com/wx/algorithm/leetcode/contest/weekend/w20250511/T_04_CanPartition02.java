package com.wx.algorithm.leetcode.contest.weekend.w20250511;

import java.util.ArrayList;
import java.util.List;

public class T_04_CanPartition02 {
    List<List<Integer>> list;

    // todo-wx 硬模拟，模拟不出来，还是心不够沉
    public boolean canPartitionGrid(int[][] grid) {
        // 前缀和
        int m = grid.length, n = grid[0].length;
        long[] preRowLine = new long[m];
        long[] preColLine = new long[n];

        list = new ArrayList<>();

        long wholeSum = 0;
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                preRowLine[i] += grid[i][j];
                preColLine[j] += grid[i][j];
                wholeSum += grid[i][j];
                row.add(grid[i][j]);
            }
            list.add(row);
        }

        long[] afterRowLine = new long[m];
        long[] afterColLine = new long[n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                afterRowLine[i] += grid[i][j];
                afterColLine[j] += grid[i][j];
            }
        }

        return canPart(grid, preRowLine, afterRowLine, wholeSum, true)
                || canPart(grid, preColLine, afterColLine, wholeSum, false);
    }

    private boolean canPart(int[][] grid, long[] linePreSum, long[] lineAfterSum, long wholeSum, boolean isRow) {
        // 前缀和、后缀和
        int m = grid.length, n = grid[0].length;

        int count = isRow ? m : n;

        for (int i = 0; i < count - 1; i++) {
            long preSum = linePreSum[i];
            long afterSum = wholeSum - preSum;

            if (preSum < afterSum) {
                long differ = afterSum - preSum;
                if (i >= count - 2) {
                    // 需要判断 最后一行 能否通过-开头和末尾的数使得preSum和afterSum相等
                    if (isRow) {
                        if (grid[i][0] == differ || grid[i][n - 1] == differ) {
                            return true;
                        }
                    } else {
                        if (grid[0][i] == differ || grid[m - 1][i] == differ) {
                            return true;
                        }
                    }
                } else {
                    if (isRow) {
                        for (int j = i; j < count; j++) {
                            for (int k = 0; k < n; k++) {
                                if (grid[j][k] == differ) {
                                    return true;
                                }
                            }
                        }
                    } else {
                        for (int j = i; j < count; j++) {
                            for (int k = 0; k < n; k++) {
                                if (grid[k][j] == differ) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            } else if (preSum > afterSum) {
                long differ = preSum - afterSum;
                if (i == 1) {
                    // 需要判断 第一行 能否通过-开头和末尾的数使得preSum和afterSum相等
                    if (isRow) {
                        if (grid[i][0] == differ || grid[i][n - 1] == differ) {
                            return true;
                        }
                    } else {
                        if (grid[0][i] == differ || grid[m - 1][i] == differ) {
                            return true;
                        }
                    }
                } else {
                    if (isRow) {
                        for (int j = 0; j < i; j++) {
                            for (int k = 0; k < n; k++) {
                                if (grid[j][k] == differ) {
                                    return true;
                                }
                            }
                        }
                    } else {
                        for (int j = 0; j < i; j++) {
                            for (int k = 0; k < n; k++) {
                                if (grid[k][j] == differ) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] nums = { { 5, 3, 1 }, { 1, 2, 1 }, { 1, 2, 3 } };
        T_04_CanPartition02 canPartition02 = new T_04_CanPartition02();
        boolean can = canPartition02.canPartitionGrid(nums);
        System.out.println(can);
    }
}
