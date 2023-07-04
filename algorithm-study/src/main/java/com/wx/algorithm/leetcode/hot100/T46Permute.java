package com.wx.algorithm.leetcode.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangxiang
 * @date 2023/7/4 09:54
 * @description
 */
public class T46Permute {

    List<List<Integer>> ans;

    public List<List<Integer>> permute(int[] nums) {
        ans = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        LinkedList<Integer> list = new LinkedList<>();
        traverse(nums, used, list);
        return ans;
    }

    private void traverse(int[] nums, boolean[] used, LinkedList<Integer> list) {
        if (list.size() == nums.length) {
            LinkedList<Integer> temp = new LinkedList<>(list);
            ans.add(temp);
            return;
        }

        // 每个递归里都从头遍历，看每个点是否使用过，如果没有则加进来，然后继续向后遍历
        // 当从递归里返回的时候，再挨个删掉
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            list.add(nums[i]);
            used[i] = true;
            traverse(nums, used, list);
            used[i] = false;
            list.removeLast();
        }
    }
}
