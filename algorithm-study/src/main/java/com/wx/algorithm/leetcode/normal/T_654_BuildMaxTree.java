package com.wx.algorithm.leetcode.normal;

import com.wx.algorithm.base.model.TreeNode;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/10
 */
public class T_654_BuildMaxTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null) return null;
        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = left; i <= right; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }

        TreeNode root = new TreeNode(max);

        root.left = buildTree(nums, left, maxIndex - 1);
        root.right = buildTree(nums, maxIndex + 1, right);
        return root;
    }


}
