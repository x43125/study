package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxiang
 * @date 2023/6/30 18:53
 * @description
 */
public class T105BuildTree {
    Map<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTree(preorder, 0, inorder.length - 1, 0);
    }

    public TreeNode buildTree(int[] preorder, int preLeft, int preRight, int inLeft) {
        if (preLeft > preRight) {
            return null;
        }

        // 前序遍历的第一个值就是当前的根节点
        TreeNode root = new TreeNode(preorder[preLeft]);
        // 该点在中序遍历中的下标
        Integer rootIndex = map.get(preorder[preLeft]);
        // 左子树的长度
        int leftTreeLength = rootIndex - inLeft;

        // 构造左子树
        root.left = buildTree(preorder, preLeft + 1, preLeft + leftTreeLength, inLeft);
        // 构造右子树
        root.right = buildTree(preorder, preLeft + leftTreeLength + 1, preRight, rootIndex + 1);

        return root;
    }
}
