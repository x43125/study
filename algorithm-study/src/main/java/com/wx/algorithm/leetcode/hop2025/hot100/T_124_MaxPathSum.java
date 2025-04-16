package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.TreeUtils;
import com.wx.algorithm.base.model.TreeNode;

public class T_124_MaxPathSum {
    private static int max;
    public int maxPathSum(TreeNode root) {
        // dfs到最深一层，开始往回撤
        // 如果左子树返回的是负值则不加
        // 如果右子树返回的是负值则不加
        // 定一个最大值时刻记录
        max = Integer.MIN_VALUE;
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int l = dfs(root.left);
        int r = dfs(root.right);

        int v = Math.max(l, r);

        int sum = root.val + (l < 0 ? 0 : l) + (r < 0 ? 0 : r);
        max = Math.max(max, sum);
        return root.val + (v < 0 ? 0 : v);
    }

    public static void main(String[] args) {
        // Integer[] nums = {-10, 9, 20, null, null, 15, 7};
        Integer[] nums = {1,2,-3,4,5,-6,null,1,null,null,-1,null,1,-10};
        TreeNode root = TreeUtils.buildTree(nums);
        T_124_MaxPathSum maxPathSum = new T_124_MaxPathSum();
        int max = maxPathSum.maxPathSum(root);
        System.out.println(max);
    }
}
