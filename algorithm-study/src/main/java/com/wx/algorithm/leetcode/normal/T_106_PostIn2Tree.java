package com.wx.algorithm.leetcode.normal;

import com.wx.algorithm.base.model.TreeNode;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/10
 */
public class T_106_PostIn2Tree {
    final Map<Integer, Integer> map = new TreeMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return build(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);

    }

    private TreeNode build(int[] postorder, int leftPost, int rightPost, int[] inorder, int leftIn, int rightIn) {
        if (leftIn > rightIn) {
            return null;
        }

        int rootVal = postorder[rightPost];
        int rootIndex = map.get(rootVal);
        int length = rootIndex - leftIn;
        TreeNode root = new TreeNode(rootVal);
        root.left = build(postorder, leftPost, leftPost + length - 1, inorder, leftIn, rootIndex - 1);
        root.right = build(postorder, leftPost + length, rightPost - 1, inorder, rootIndex + 1, rightIn);
        return root;
    }


}
