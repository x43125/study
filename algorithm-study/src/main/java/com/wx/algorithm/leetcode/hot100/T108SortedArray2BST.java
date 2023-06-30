package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.ListNode;
import com.wx.algorithm.base.model.TreeNode;

/**
 * @author wangxiang
 * @date 2023/6/30 11:14
 * @description
 */
public class T108SortedArray2BST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 不需要特殊判断 left==right的场景，因为下一个递归会处理
        int mid = (right - left) << 1 + left;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildTree(nums, left, mid);
        node.right = buildTree(nums, mid + 1, right);
        return node;
    }
}
