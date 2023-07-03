package com.wx.algorithm.leetcode.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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


    int[] inDeg;

    /**
     * 难点在于如何维护边、节点、入度、出度的信息
     * 像本题，使用了一个集合 edges 来表示某个节点指向的节点；使用了一个数组 inDeg 来表示某个节点的入度
     * 通过维护 inDeg 来查看是否能够找到目标值
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishIn(int numCourses, int[][] prerequisites) {
        // 初始化
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        // 初始化 所有节点的入度
        inDeg = new int[numCourses];
        for (int[] info : prerequisites) {
            // 节点info[0]的前置节点info[1]
            // 记录info[1]节点的出边
            edges.get(info[1]).add(info[0]);
            // 节点info[0]的入度+1
            ++inDeg[info[0]];
        }

        // 记录所有入度为0的节点
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDeg[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            // 扫描的个数
            ++visited;
            int u = queue.poll();
            // 当前节点 u 的所有出边 即u指向的下一个节点
            for (Integer v : edges.get(u)) {
                // 下一个节点的入度-1
                --inDeg[v];
                // 如果该点的入度为0，则维护进队列里
                if (inDeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return visited == numCourses;
    }
}
