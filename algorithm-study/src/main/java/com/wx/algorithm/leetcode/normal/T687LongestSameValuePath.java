package com.wx.algorithm.leetcode.normal;

import com.wx.algorithm.base.model.TreeNode;

/*
 * @Author: wangxiang wangxiang@flashhold.com
 * @Date: 2022-09-02 19:03:07
 * @LastEditors: wangxiang wangxiang@flashhold.com
 * @LastEditTime: 2022-09-03 13:58:58
 * @FilePath: \algorithm-study\src\main\java\com\wx\algorithm\leetcode\normal\T687LongestSameValuePath.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
/*
 * @lc app=leetcode.cn id=687 lang=java
 *
 * [687] 最长同值路径
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class T687LongestSameValuePath {
    int res;

    public int longestUnivaluePath(TreeNode root) {
        res = 0;
        dfs(root);
        return res;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right); 

        int left1 = 0, right1 = 0;
        if (root.left != null && root.left.val == root.val) {
            left1 = left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            right1 = right + 1;
        }

        res = Math.max(res, left1 + right1);
        return Math.max(left1, right1);
    }
}
// @lc code=end

