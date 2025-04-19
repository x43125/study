package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class T_207_Topology {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 初始化节点入度：某节课直接依赖几门课
        int[] dependencies = new int[numCourses];
        // next节点，某门课被哪些课依赖
        Map<Integer, List<Integer>> classBased = new HashMap<>();
        for (int[] pre : prerequisites) {
            dependencies[pre[0]]++;

            if (!classBased.containsKey(pre[1])) {
                classBased.put(pre[1], new ArrayList<>());
            }
            classBased.get(pre[1]).add(pre[0]);
        }

        // 从入度=0的开始，不断的向后处理
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < dependencies.length; i++) {
            if (dependencies[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            List<Integer> based = classBased.get(poll);
            if (based != null) {
                for (Integer base : based) {
                    // 减少依赖的入度
                    dependencies[base]--;
                    // 将新的0度入queue
                    if (dependencies[base] == 0) {
                        queue.offer(base);
                    }
                }
            }
            classBased.remove(poll);
        }

        for (int dep : dependencies) {
            if (dep > 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = { { 1, 0 }, {2, 1}, {0, 1} };
        T_207_Topology t_207_Topology = new T_207_Topology();
        boolean canFinish = t_207_Topology.canFinish(numCourses, prerequisites);
        System.out.println(canFinish);
    }
}