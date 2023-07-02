package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.TreeNode;

/**
 * @author wangxiang
 * @date 2023/7/1 18:48
 * @description
 */
public class T437PathSum {

    /**
     * 暴力法遍历每一个节点
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ans = traverse(root, targetSum);
        ans += pathSum(root.left, targetSum);
        ans += pathSum(root.right, targetSum);

        return ans;
    }

    private int traverse(TreeNode node, long targetSum) {
        if (node == null) {
            return 0;
        }

        int ans = 0;
        if (node.val == targetSum) {
            ans++;
        }
        ans += traverse(node.left, targetSum - node.val);
        ans += traverse(node.right, targetSum - node.val);
        return ans;
    }
}
