package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class T_56_MergeArr {
    public int[][] merge(int[][] intervals) {
        // 排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });

        int n = intervals.length;
        List<int[]> res = new ArrayList<>();
        // 初始化第一个
        res.add(new int[] { intervals[0][0], intervals[0][1] });

        // 双指针
        int i = 0, j = 1;
        while (j < n) {
            int preR = res.get(i)[1];
            int nowL = intervals[j][0];
            int nowR = intervals[j][1];
            
            if (preR < nowL) {
                // 不符合merge要求
                res.add(new int[] { nowL, nowR });
                i++;
            } else if (preR == nowL || preR < nowR) {
                // 符合
                res.get(i)[1] = nowR;
            }
            j++;
        }

        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        int[][] nums = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        T_56_MergeArr mergeArr = new T_56_MergeArr();
        int[][] merged = mergeArr.merge(nums);
        for (int[] m : merged) {
            System.out.println(m[0] + " " + m[1]);
        }
    }
}
