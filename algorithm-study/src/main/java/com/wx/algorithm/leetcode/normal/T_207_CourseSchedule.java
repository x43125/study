package com.wx.algorithm.leetcode.normal;

import java.util.LinkedList;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/06/03
 */
public class T_207_CourseSchedule {
    public static void main(String[] args) {
        int numCourses = 3;
        int[][] prerequisites = {{1, 0}, {0, 2}};
        T_207_CourseSchedule courseSchedule = new T_207_CourseSchedule();
        boolean res = courseSchedule.canFinish(numCourses, prerequisites);
        System.out.println("res = " + res);
    }

    boolean[] onPath;
    boolean[] visited;
    boolean hasCycle;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        onPath = new boolean[numCourses];
        visited = new boolean[numCourses];
        hasCycle = false;

        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        // 有环说明课程无法修完
        return !hasCycle;
    }

    private void traverse(List<Integer>[] graph, int i) {
        // 出现环
        if (onPath[i]) {
            hasCycle = true;
            return;
        }

        // 遍历过了，或者 已经有环了
        if (visited[i] || hasCycle) {
            return;
        }
        // 前序代码位置
        visited[i] = true;
        onPath[i] = true;
        for (Integer integer : graph[i]) {
            traverse(graph, integer);
        }
        // 后续代码位置
        onPath[i] = false;
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new LinkedList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];

            graph[from].add(to);
        }

        return graph;
    }
}
