package com.wx.algorithm.leetcode.contest.weekend.w20250511;

public class T_02_CanPartition01 {
    public boolean canPartitionGrid(int[][] grid) {
        // 前缀和
        int m = grid.length, n = grid[0].length;
        long[] rowLine = new long[m];
        long[] colLine = new long[n];

        long wholeSum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowLine[i] += grid[i][j];
                colLine[j] += grid[i][j];
                wholeSum += grid[i][j];
            }
        }

        if (wholeSum % 2 != 0) {
            return false;
        }

        return canPart(rowLine, wholeSum / 2) || canPart(colLine, wholeSum / 2);
    }

    private boolean canPart(long[] lineSum, long target) {
        long sum = 0;
        for (long num : lineSum) {
            sum += num;
            if (sum == target) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] nums = { { 5, 3, 1 }, { 1, 2, 1 }, { 1, 2, 2 } };
        T_02_CanPartition01 canPartition01 = new T_02_CanPartition01();
        boolean can = canPartition01.canPartitionGrid(nums);
        System.out.println(can);
    }
}
