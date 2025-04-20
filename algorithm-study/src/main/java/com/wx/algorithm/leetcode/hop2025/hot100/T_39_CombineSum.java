package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class T_39_CombineSum {
    private static List<List<Integer>> result;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        result = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, new LinkedList<Integer>(), 0);
        return result;
    }

    private static void dfs(int[] candidates, int target, LinkedList<Integer> list, int index) {
        if (0 == target) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            int candidate = candidates[i];
            // 剪枝的位置要注意，此处控制大于的情况不会到下一级，因此上面只需要判断 0==target即可
            if (candidate > target) {
                return;
            }
            list.add(candidate);
            dfs(candidates, target - candidate, list, i);
            list.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] candidates = { 2, 3, 6, 7, 1,10,5, 8 };
        int target = 7;
        T_39_CombineSum combineSum = new T_39_CombineSum();
        List<List<Integer>> combinationSum = combineSum.combinationSum(candidates, target);
        combinationSum.forEach(list -> {
            list.forEach(System.out::print);
            System.out.println();
        });
    }
}
