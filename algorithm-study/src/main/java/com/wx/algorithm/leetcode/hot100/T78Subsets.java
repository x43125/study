package com.wx.algorithm.leetcode.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangxiang
 * @date 2023/7/4 10:13
 * @description
 */
public class T78Subsets {
    List<List<Integer>> ans;
    LinkedList<Integer> curr;

    public List<List<Integer>> subsets(int[] nums) {
        ans = new LinkedList<>();
        curr = new LinkedList<>();
        subsets(nums, 0);

        return ans;
    }

    private void subsets(int[] nums, int left) {
        // 所以每次都将curr添加进ans中即可
        ans.add(new LinkedList<>(curr));

        // 因为每次都从left向后++，所以每次的值其实都不一样
        while (left < nums.length) {
            curr.add(nums[left]);
            subsets(nums, left + 1);
            curr.removeLast();
            left++;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        T78Subsets subsets = new T78Subsets();
        List<List<Integer>> subsets1 = subsets.subsets(nums);
        subsets1.forEach(list -> {
            list.forEach(System.out::print);
            System.out.println();
        });
    }
}
