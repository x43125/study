package com.wx.algorithm.leetcode.normal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=491 lang=java
 *
 * [491] 递增子序列
 */

// @lc code=start
class T491IncreaseSubSequences {

    List<List<Integer>> res;
    List<Integer> list;

    public List<List<Integer>> findSubsequences(int[] nums) {
        res = new ArrayList<>();
        list = new ArrayList<>();

        if (nums.length == 0) {
            return res;
        }

        findSubsequence(0, nums);
        return res;
    }

    private void findSubsequence(int start, int[] nums) {
        if (list.size() >= 2) {
            res.add(new ArrayList<>(list));
        }

        Set<Integer> used = new HashSet<>();

        for (int i = start; i < nums.length; i++) {
            if (!list.isEmpty() && list.get(list.size()-1) > nums[i]) {
                continue;
            }

            if (used.contains(nums[i])) {
                continue;
            }
            
            used.add(nums[i]);
            list.add(nums[i]);
            findSubsequence(i + 1, nums);
            list.remove(list.size() - 1);
        }
    }
}
// @lc code=end
