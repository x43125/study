package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.LinkedList;
import java.util.List;

public class T_46_FullPermute {
    List<List<Integer>> result;

    public List<List<Integer>> permute(int[] nums) {
        result = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        doPermute(nums, used, list);
        return result;
    }

    private void doPermute(int[] nums, boolean[] used, LinkedList<Integer> list) {
        if (list.size() == nums.length) {
            List<Integer> curr = new LinkedList<>(list);
            result.add(curr);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            list.add(nums[i]);
            used[i] = true;
            doPermute(nums, used, list);
            // 回溯
            list.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        T_46_FullPermute fullPermute = new T_46_FullPermute();
        List<List<Integer>> permute = fullPermute.permute(nums);
        permute.forEach(list -> {
            list.forEach(t -> System.out.print(t + " "));
            System.out.println();
        });
    }
}
