package com.wx.algorithm.leetcode.hot100;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 经典的一题：3种解法，DFS，BFS，并查集
 *
 * @author wangxiang
 * @date 2023/7/3 09:56
 * @description
 */
public class T200NumIsLands {

    /**
     * DFS: 深度优先搜索：中序遍历：递归
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    dfs(grid, i, j);
                }
            }
        }

        return ans;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;

        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    /**
     * BFS：广度优先搜索：层序：Queue
     *
     * @param grid
     * @return
     */
    public int numIslandsOptimize(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length, n = grid[0].length;
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    grid[i][j] = '0';

                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        int x = curr[0], y = curr[1];
                        if (x - 1 >= 0 && grid[x - 1][y] == '1') {
                            grid[x - 1][y] = '0';
                            queue.offer(new int[]{x - 1, y});
                        }
                        if (x + 1 < m && grid[x + 1][y] == '1') {
                            grid[x + 1][y] = '0';
                            queue.offer(new int[]{x + 1, y});
                        }
                        if (y - 1 >= 0 && grid[x][y - 1] == '1') {
                            grid[x][y - 1] = '0';
                            queue.offer(new int[]{x, y - 1});
                        }
                        if (y + 1 < n && grid[x][y + 1] == '1') {
                            grid[x][y + 1] = '0';
                            queue.offer(new int[]{x, y + 1});
                        }
                    }
                }
            }
        }

        return ans;
    }

    int[] p;
    int res;

    /**
     * 并查集
     *
     * @param grid
     * @return
     */
    public int numIslandsOptimize2(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        p = new int[m * n];
        res = 0;

        //初始化 parent 数组，记录初始岛屿数（也就是 1 的数目）
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int idx = i * n + j;
                p[idx] = idx;
                if (grid[i][j] == '1') {
                    res++;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int idx = i * n + j;
                if (grid[i][j] == '1') {
                    if (i + 1 < m && grid[i + 1][j] == '1') { //合并岛屿
                        union(idx, (i + 1) * n + j);
                    }
                    if (j + 1 < n && grid[i][j + 1] == '1') {
                        union(idx, i * n + j + 1);
                    }
                }
            }
        }
        return res;
    }

    /**
     * 查
     *
     * @param i
     * @return
     */
    private int find(int i) {
        if (p[i] == i) {
            return p[i];
        }
        return p[i] = find(p[i]);
    }

    /**
     * 合并
     *
     * @param i
     * @param j
     */
    private void union(int i, int j) {
        //避免重复合并操作
        if (find(i) == find(j)) {
            return;
        }
        p[find(i)] = p[find(j)];
        res--;
    }
}
