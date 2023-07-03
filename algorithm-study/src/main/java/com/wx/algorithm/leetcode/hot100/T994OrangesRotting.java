package com.wx.algorithm.leetcode.hot100;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wangxiang
 * @date 2023/7/3 11:13
 * @description
 */
public class T994OrangesRotting {

    /**
     * 广度优先搜索
     * 先把所有的烂橘子都放到队列里，并且要维护每一次的烂橘子的个数，再记录所有新鲜橘子的个数
     * 然后每次出队列将相邻是橘子的再入队列，直到当当前队列个数的都出了，代表第一波烂的橘子，
     *
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        int rottingCount = queue.size();
        int ans = 0;
        while (!queue.isEmpty()) {
            if (rottingCount == 0) {
                ans++;
                rottingCount = queue.size();
            }

            int[] curr = queue.poll();
            int x = curr[0], y = curr[1];

            if (x - 1 >= 0 && grid[x - 1][y] == 1) {
                grid[x - 1][y] = 2;
                queue.offer(new int[]{x - 1, y});
                freshCount--;
            }

            if (x + 1 < m && grid[x + 1][y] == 1) {
                grid[x + 1][y] = 2;
                queue.offer(new int[]{x + 1, y});
                freshCount--;
            }

            if (y - 1 >= 0 && grid[x][y - 1] == 1) {
                grid[x][y - 1] = 2;
                queue.offer(new int[]{x, y - 1});
                freshCount--;
            }

            if (y + 1 < n && grid[x][y + 1] == 1) {
                grid[x][y + 1] = 2;
                queue.offer(new int[]{x, y + 1});
                freshCount--;
            }

            rottingCount--;
        }

        return freshCount == 0 ? ans : -1;
    }
}
