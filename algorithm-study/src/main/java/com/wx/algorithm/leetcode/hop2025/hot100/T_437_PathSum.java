package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.HashMap;
import java.util.Map;

import com.wx.algorithm.base.TreeUtils;
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
        preSum += root.val;

        int count = prefix.getOrDefault(preSum - targetSum, 0);
        prefix.put(preSum, prefix.getOrDefault(preSum, 0) + 1);
        count += dfs(root.left, prefix, preSum, targetSum);
        count += dfs(root.right, prefix, preSum, targetSum);

        prefix.put(preSum, prefix.getOrDefault(preSum, 0) - 1);
        
        return count;
    }

    public static void main(String[] args) {
        Integer[] nums = {10,5,-3,3,2,null,11,3,-2,null,1};
        TreeNode root = TreeUtils.buildTree(nums);
        T_437_PathSum pathSum = new T_437_PathSum();
        int count = pathSum.pathSum(root, 8);
        System.out.println(8 + ":" + count);
    }
}
