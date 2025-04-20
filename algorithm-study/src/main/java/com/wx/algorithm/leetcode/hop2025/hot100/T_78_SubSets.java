package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.LinkedList;
import java.util.List;

public class T_78_SubSets {

    List<List<Integer>> result;
    LinkedList<Integer> list;

    public List<List<Integer>> subsets(int[] nums) {
        result = new LinkedList<>();
        list = new LinkedList<>();
        permute(nums, 0);
        return result;
    }

    private void permute(int[] nums, int start) {
        result.add(new LinkedList<>(list));

        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            permute(nums, i + 1);
            list.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        T_78_SubSets t_78_SubSets = new T_78_SubSets();
        List<List<Integer>> subsets = t_78_SubSets.subsets(nums);
        subsets.forEach(list -> {
            list.forEach(t -> System.out.print(t + " "));
            System.out.println();
        });
    }
}
