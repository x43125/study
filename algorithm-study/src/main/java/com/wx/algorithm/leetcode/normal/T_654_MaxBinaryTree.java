package com.wx.algorithm.leetcode.normal;


import com.wx.algorithm.base.model.TreeNode;

/**
 * @author wangxiang
 * @date 2022/8/20 10:18
 * @description
 */
public class T_654_MaxBinaryTree {
    public static void main(String[] args) {

    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return traverse(nums, 0, nums.length - 1);
    }

    private TreeNode traverse(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int max = Integer.MIN_VALUE;
        int maxI = 0;
        for (int i = left; i <= right; i++) {
            if (max < nums[i]) {
                max = nums[i];
                maxI = i;
            }
        }

        TreeNode root = new TreeNode(max);
        root.left = traverse(nums, left, maxI - 1);
        root.right = traverse(nums, maxI + 1, right);
        return root;
    }
}
