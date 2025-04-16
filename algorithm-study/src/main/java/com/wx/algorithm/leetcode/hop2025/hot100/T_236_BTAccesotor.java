package com.wx.algorithm.leetcode.hop2025.hot100;

import javax.swing.plaf.TreeUI;

import com.wx.algorithm.base.TreeUtils;
import com.wx.algorithm.base.model.TreeNode;

public class T_236_BTAccesotor {
    private TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ans = null;
        this.dfs(root, p, q);
        return this.ans;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }

        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);

        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson)) ) {
            ans = root;
        }

        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    public static void main(String[] args) {
        Integer[] nums = {3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root = TreeUtils.buildTree(nums);
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);
        T_236_BTAccesotor btAccesotor = new T_236_BTAccesotor();
        TreeNode lowestCommonAncestor = btAccesotor.lowestCommonAncestor(root, p, q);
        System.out.println(lowestCommonAncestor.val);
    }
}
