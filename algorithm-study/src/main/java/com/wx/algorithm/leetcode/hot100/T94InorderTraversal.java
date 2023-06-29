package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxiang
 * @date 2023/6/30 07:45
 * @description
 */
public class T94InorderTraversal {
    List<Integer> ans;

    public List<Integer> inorderTraversal(TreeNode root) {
        ans = new ArrayList<>();
        traverse(root);
        return ans;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);
        ans.add(root.val);
        traverse(root.right);
    }
}
