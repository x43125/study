package com.wx.algorithm.labuladong.rollback;

import java.util.LinkedList;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/21
 */
public class SubSets {
    public List<List<Integer>> subsets(int[] nums) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        backTrack(nums, 0);
        return res;
    }

    List<List<Integer>> res;
    LinkedList<Integer> track;

    private void backTrack(int[] nums, int start) {
        res.add(new LinkedList<>(track));

        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backTrack(nums, i + 1);
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        SubSets subSets = new SubSets();
        List<List<Integer>> subsets = subSets.subsets(nums);
        for (List<Integer> subset : subsets) {
            subset.forEach(k -> System.out.print(k + " "));
            System.out.println();
        }
    }
}
