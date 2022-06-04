package com.wx.algorithm.leetcode.normal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/06/03
 */
public class T_210_CourseSchedule2 {
    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        T_210_CourseSchedule2 courseSchedule2 = new T_210_CourseSchedule2();
        int[] res = courseSchedule2.findOrder(numCourses, prerequisites);
        for (int re : res) {
            System.out.print(re + " ");
        }
    }

    List<Integer> postOrder;
    boolean hasCycle;
    boolean[] visited, onPath;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        postOrder = new ArrayList<>();
        hasCycle = false;
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];

//        生成图结构 邻接表形式
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
//        如果有环，则无可行路径，返回空的
        if (hasCycle) {
            return new int[]{};
        }

//        倒排序
        Collections.reverse(postOrder);

        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = postOrder.get(i);
        }

        return res;
    }

    private void traverse(List<Integer>[] graph, int i) {
//        如果有环
        if (onPath[i]) {
            hasCycle = true;
            return;
        }
//        如果已经遍历过，或已经找到环
        if (visited[i] || hasCycle) {
            return;
        }
//        开始遍历递归
        onPath[i] = true;
        visited[i] = true;
        List<Integer> list = graph[i];
        for (Integer t : list) {
            traverse(graph, t);
        }
//        开始回溯
        postOrder.add(i);
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
