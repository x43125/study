package com.wx.algorithm.leetcode.normal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Descrption: 遍历DAG图，返回从0 到 n-1 的所有路径
 * @Author: x43125
 * @Date: 22/06/03
 */
public class T_797_AllPathsFromSource2Target {
    public static void main(String[] args) {
        int[][] graph = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        T_797_AllPathsFromSource2Target source2Target = new T_797_AllPathsFromSource2Target();
        List<List<Integer>> res = source2Target.allPathsSourceTarget(graph);
        for (List<Integer> list : res) {
            list.forEach(System.out::print);
            System.out.println();
        }
    }


    List<List<Integer>> res;

    /**
     * 因是有向无环图，所以无需visited数组
     *
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, path, 0);
        return res;
    }

    private void traverse(int[][] graph, LinkedList<Integer> path, int s) {
        path.addLast(s);
        int n = graph.length;

        if (s == n - 1) {
            res.add(new LinkedList<>(path));
            path.removeLast();
            return;
        }

        for (int value : graph[s]) {
            traverse(graph, path, value);
        }

        path.removeLast();
    }


}
