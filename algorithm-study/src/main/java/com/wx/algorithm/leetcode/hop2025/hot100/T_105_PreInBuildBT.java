package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.HashMap;
import java.util.Map;

import com.wx.algorithm.base.TreeUtils;
import com.wx.algorithm.base.model.TreeNode;

public class T_105_PreInBuildBT {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }

        return traverse(indexMap, inorder, 0, inorder.length - 1, preorder, 0, inorder.length - 1);
    }

    private TreeNode traverse(Map<Integer, Integer> indexMap, int[] inorder, int left, int right, int[] preorder,
            int preLeft, int preRight) {
        if (left > right) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preLeft]);
        Integer index = indexMap.get(preorder[preLeft]);
        root.left = traverse(indexMap, inorder, left, index - 1, preorder, preLeft + 1, preLeft + 1 + index - left - 1);
        root.right = traverse(indexMap, inorder, index + 1, right, preorder, preLeft + 1 + index - left, preRight);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = { 3, 9, 20, 15, 7 };
        int[] inorder = { 9, 3, 15, 20, 7 };
        T_105_PreInBuildBT preInBuildBT = new T_105_PreInBuildBT();
        TreeNode root = preInBuildBT.buildTree(preorder, inorder);
        TreeUtils.printTree(root);
    }
}
