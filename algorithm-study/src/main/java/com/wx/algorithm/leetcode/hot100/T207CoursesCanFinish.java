package com.wx.algorithm.leetcode.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxiang
 * @date 2023/7/3 13:36
 * @description
 */
public class T207CoursesCanFinish {
    List<List<Integer>> edges;
    int[] visited;
    boolean valid;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        valid = true;

        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }

        visited = new int[numCourses];

        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    private void dfs(int u) {
        visited[u] = 1;
        for (Integer v : edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }

        visited[u] = 2;
    }

    public static void main(String[] args) {
        int numCourses = 100;
        int[][] prerequisites = {{1, 0}, {0, 2}};

        T207CoursesCanFinish coursesCanFinish = new T207CoursesCanFinish();
        boolean canFinish = coursesCanFinish.canFinish(numCourses, prerequisites);
        System.out.println("canFinish = " + canFinish);
    }
}
