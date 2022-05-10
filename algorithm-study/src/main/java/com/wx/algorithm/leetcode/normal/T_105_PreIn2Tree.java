package com.wx.algorithm.leetcode.normal;

import com.wx.algorithm.base.model.TreeNode;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/10
 */
public class T_105_PreIn2Tree {
    final Map<Integer, Integer> map = new TreeMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int leftPre, int rightPre, int[] inorder, int leftIn, int rightIn) {
        if (leftPre > rightPre) {
            return null;
        }

        int rootVal = preorder[leftPre];
        int rootIndex = map.get(rootVal);

        TreeNode root = new TreeNode(rootVal);
        int length = rootIndex - leftIn;
        root.left = build(preorder, leftPre + 1, leftPre + length, inorder, leftIn, rootIndex - 1);
        root.right = build(preorder, leftPre + length + 1, rightPre, inorder, rootIndex + 1, rightIn);
        return root;
    }


}
