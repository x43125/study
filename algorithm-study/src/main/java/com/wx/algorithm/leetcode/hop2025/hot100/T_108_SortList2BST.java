package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.TreeUtils;
import com.wx.algorithm.base.model.TreeNode;

public class T_108_SortList2BST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return traverse(nums, 0, nums.length-1);
    }

    private TreeNode traverse(int[] nums, int head, int tail) {
        if (head == tail) {
            return new TreeNode(nums[head]);
        }
        if (head > tail) {
            return null;
        }
        int mid = (tail - head) / 2 + head;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = traverse(nums, head, mid-1);
        root.right = traverse(nums, mid+1, tail);
        return root;
    }

    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        T_108_SortList2BST sortList2BST = new T_108_SortList2BST();
        TreeNode root = sortList2BST.sortedArrayToBST(nums);
        TreeUtils.printTree(root);
    }
}
