package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 前缀和
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSumOptimize(TreeNode root, int targetSum) {
        // 前缀和: 和：数量
        Map<Long, Integer> preSum = new HashMap<>();
        preSum.put(0L, 1);
        // 深度优先遍历
        return dfs(root, preSum, 0, targetSum);
    }

    private int dfs(TreeNode node, Map<Long, Integer> preSum, long curr, int targetSum) {
        if (node == null) {
            return 0;
        }

        int ans;
        // 当前节点的前缀和
        curr += node.val;

        // 如果在前缀和中有 curr-targetSum 就说明有从某些点到当前点和==targetSum的路径
        // 否则ans = 0
        ans = preSum.getOrDefault(curr - targetSum, 0);
        // 维护前缀和map,将curr也添加进去
        preSum.put(curr, preSum.getOrDefault(curr, 0) + 1);
        ans += dfs(node.left, preSum, curr, targetSum);
        ans += dfs(node.right, preSum, curr, targetSum);
        preSum.put(curr, preSum.getOrDefault(curr, 0) - 1);

        return ans;
    }
}
