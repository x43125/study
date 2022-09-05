package com.wx.algorithm.leetcode.normal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wx.algorithm.base.model.TreeNode;

/*
 * @Author: wangxiang wangxiang@flashhold.com
 * @Date: 2022-09-05 10:34:26
 * @LastEditors: wangxiang wangxiang@flashhold.com
 * @LastEditTime: 2022-09-05 10:55:21
 * @FilePath: \algorithm-study\src\main\java\com\wx\algorithm\leetcode\normal\652.寻找重复的子树.java
 * @Description: 这种有返回值的递归，还是不太熟
 *   先声明一个全局的结果集来储存最终的返回
 *   然后先递归左子树，再递归右子树
 *   接着做一些判断操作，结果集得到一个，或者不符合要求不得到
 *   最后把之前递归得到的某个结果 以某种运算后返回回去，这样上一层父节点就可以得到子节点的信息了
 */
/*
 * @lc app=leetcode.cn id=652 lang=java
 *
 * [652] 寻找重复的子树
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
class Solution {

    Map<String, Integer> memo;
    List<TreeNode> res;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        memo = new HashMap<>();
        res = new ArrayList<>();
        traverse(root);
        return res;
    }

    private String traverse(TreeNode root) {
        if (root == null) {
            return "#";
        }
        
        String left = traverse(root.left);
        String right = traverse(root.right);

        String subTree = left + "," + right + "," + root.val;

        int freq = memo.getOrDefault(subTree, 0);

        if (freq == 1) {
            res.add(root);
        }

        memo.put(subTree, freq+1);
        return subTree;
    }
}
// @lc code=end

