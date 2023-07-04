package com.wx.algorithm.leetcode.hot100;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangxiang
 * @date 2023/7/4 11:25
 * @description
 */
public class T39CombinationSum {
    List<List<Integer>> ans;
    LinkedList<Integer> curr;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new LinkedList<>();
        curr = new LinkedList<>();
        // 排序之后,如果和>target则可以直接返回
        Arrays.sort(candidates);
        traverse(candidates, 0, target, curr);
        return ans;
    }

    private void traverse(int[] candidates, int start, int target, LinkedList<Integer> curr) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            ans.add(new LinkedList<>(curr));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            curr.add(candidates[i]);
            traverse(candidates, i, target - candidates[i], curr);
            curr.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] candidates = {1,2, 3, 6, 7};
        int target = 7;
        T39CombinationSum combinationSum = new T39CombinationSum();
        List<List<Integer>> lists = combinationSum.combinationSum(candidates, target);
        lists.forEach(list -> {
            list.forEach(System.out::print);
            System.out.println();
        });

    }
}
