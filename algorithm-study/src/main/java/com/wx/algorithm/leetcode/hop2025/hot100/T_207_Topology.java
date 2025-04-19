package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;

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

    private static int[] studied;
    private static boolean canFinish;

    /**
     * 超时
     * 本题的其他解法 dfs
     * 
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish_02(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            return true;
        }
        canFinish = false;
        studied = new int[numCourses];
        Map<Integer, List<Integer>> classBased = new HashMap<>();
        for (int[] pre : prerequisites) {
            if (!classBased.containsKey(pre[1])) {
                classBased.put(pre[1], new ArrayList<>());
            }
            classBased.get(pre[1]).add(pre[0]);
        }

        for (Map.Entry<Integer, List<Integer>> entry : classBased.entrySet()) {
            canFinish = dfs(classBased, entry.getKey());
            if (!canFinish) {
                return false;
            }
        }
        return canFinish;
    }

    private boolean dfs(Map<Integer, List<Integer>> classBased, Integer key) {
        if (studied[key] == 1) {
            return false;
        }

        if (!classBased.containsKey(key)) {
            return true;
        }

        studied[key] = 1;
        List<Integer> based = classBased.get(key);
        for (Integer base : based) {
            boolean canFinish = dfs(classBased, base);
            if (!canFinish) {
                return false;
            }
        }
        studied[key] = 0;
        return true;
    }

    public static void main(String[] args) {
        int numCourses = 100;
        // int[][] prerequisites = { { 1, 0 }, { 2, 1 } };
        int[][] prerequisites = { { 1, 0 }, { 2, 0 }, { 2, 1 }, { 3, 1 }, { 3, 2 }, { 4, 2 }, { 4, 3 }, { 5, 3 },
                { 5, 4 }, { 6, 4 }, { 6, 5 }, { 7, 5 }, { 7, 6 }, { 8, 6 },
                { 8, 7 }, { 9, 7 }, { 9, 8 }, { 10, 8 }, { 10, 9 }, { 11, 9 }, { 11, 10 }, { 12, 10 }, { 12, 11 },
                { 13, 11 }, { 13, 12 }, { 14, 12 }, { 14, 13 }, { 15, 13 }, { 15, 14 },
                { 16, 14 }, { 16, 15 }, { 17, 15 }, { 17, 16 }, { 18, 16 }, { 18, 17 }, { 19, 17 }, { 19, 18 },
                { 20, 18 }, { 20, 19 }, { 21, 19 }, { 21, 20 }, { 22, 20 }, { 22, 21 },
                { 23, 21 }, { 23, 22 }, { 24, 22 }, { 24, 23 }, { 25, 23 }, { 25, 24 }, { 26, 24 }, { 26, 25 },
                { 27, 25 }, { 27, 26 }, { 28, 26 }, { 28, 27 }, { 29, 27 }, { 29, 28 },
                { 30, 28 }, { 30, 29 }, { 31, 29 }, { 31, 30 }, { 32, 30 }, { 32, 31 }, { 33, 31 }, { 33, 32 },
                { 34, 32 }, { 34, 33 }, { 35, 33 }, { 35, 34 }, { 36, 34 }, { 36, 35 },
                { 37, 35 }, { 37, 36 }, { 38, 36 }, { 38, 37 }, { 39, 37 }, { 39, 38 }, { 40, 38 }, { 40, 39 },
                { 41, 39 }, { 41, 40 }, { 42, 40 }, { 42, 41 }, { 43, 41 }, { 43, 42 },
                { 44, 42 }, { 44, 43 }, { 45, 43 }, { 45, 44 }, { 46, 44 }, { 46, 45 }, { 47, 45 }, { 47, 46 },
                { 48, 46 }, { 48, 47 }, { 49, 47 }, { 49, 48 }, { 50, 48 }, { 50, 49 },
                { 51, 49 }, { 51, 50 }, { 52, 50 }, { 52, 51 }, { 53, 51 }, { 53, 52 }, { 54, 52 }, { 54, 53 },
                { 55, 53 }, { 55, 54 }, { 56, 54 }, { 56, 55 }, { 57, 55 }, { 57, 56 },
                { 58, 56 }, { 58, 57 }, { 59, 57 }, { 59, 58 }, { 60, 58 }, { 60, 59 }, { 61, 59 }, { 61, 60 },
                { 62, 60 }, { 62, 61 }, { 63, 61 }, { 63, 62 }, { 64, 62 }, { 64, 63 },
                { 65, 63 }, { 65, 64 }, { 66, 64 }, { 66, 65 }, { 67, 65 }, { 67, 66 }, { 68, 66 }, { 68, 67 },
                { 69, 67 }, { 69, 68 }, { 70, 68 }, { 70, 69 }, { 71, 69 }, { 71, 70 },
                { 72, 70 }, { 72, 71 }, { 73, 71 }, { 73, 72 }, { 74, 72 }, { 74, 73 }, { 75, 73 }, { 75, 74 },
                { 76, 74 }, { 76, 75 }, { 77, 75 }, { 77, 76 }, { 78, 76 }, { 78, 77 },
                { 79, 77 }, { 79, 78 }, { 80, 78 }, { 80, 79 }, { 81, 79 }, { 81, 80 }, { 82, 80 }, { 82, 81 },
                { 83, 81 }, { 83, 82 }, { 84, 82 }, { 84, 83 }, { 85, 83 }, { 85, 84 },
                { 86, 84 }, { 86, 85 }, { 87, 85 }, { 87, 86 }, { 88, 86 }, { 88, 87 }, { 89, 87 }, { 89, 88 },
                { 90, 88 }, { 90, 89 }, { 91, 89 }, { 91, 90 }, { 92, 90 }, { 92, 91 },
                { 93, 91 }, { 93, 92 }, { 94, 92 }, { 94, 93 }, { 95, 93 }, { 95, 94 }, { 96, 94 }, { 96, 95 },
                { 97, 95 }, { 97, 96 }, { 98, 96 }, { 98, 97 }, { 99, 97 } };
        T_207_Topology t_207_Topology = new T_207_Topology();
        boolean canFinish = t_207_Topology.canFinish_02(numCourses, prerequisites);
        System.out.println(canFinish);
    }
}