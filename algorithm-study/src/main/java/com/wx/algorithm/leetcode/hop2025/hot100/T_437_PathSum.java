package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.HashMap;
import java.util.Map;

import com.wx.algorithm.base.model.TreeNode;

public class T_437_PathSum {
    public int pathSum(TreeNode root, int targetSum) {
        // 前缀和及个数
        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    private int dfs(TreeNode root, Map<Long, Integer> prefix, long preSum, int targetSum) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        preSum += root.val;

        count = prefix.getOrDefault(preSum - targetSum, 0);
        prefix.put(preSum, prefix.getOrDefault(preSum, 0) + 1);
        count += dfs(root.left, prefix, preSum, targetSum);
        count += dfs(root.right, prefix, preSum, targetSum);

        prefix.put(preSum, prefix.getOrDefault(preSum, 0) - 1);
        
        return count;
    }

    private TreeNode buildPrefix(TreeNode root, int preSum) {
        if (root == null) {
            return null;
        }

        preSum = preSum + root.val;
        TreeNode node = new TreeNode(preSum);
        node.left = buildPrefix(root.left, preSum);
        node.right = buildPrefix(root.right, preSum);
        return node;
    }
}
