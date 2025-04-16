package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.TreeUtils;
import com.wx.algorithm.base.model.TreeNode;

public class T_236_BTAccesotor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 遍历，如果当前节点为空，或为p或q，则返回
        if (root == null || root == p || root == q) {
            return root;
        }

        // 遍历左子树，返回最先遇到的p/q，如果左子树返回空，说明遍历到底没找到p或q，则pq在右子树
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        // 遍历右子树，返回最先遇到的p/q，如果右子树返回空，说明遍历到底没找到p或q，则pq在左子树
        TreeNode r = lowestCommonAncestor(root.right, p, q);

        // 如果左右子树均不为空，则当前节点就是目标值
        return l == null ? r : (r == null ? l : root);
    }

    public static void main(String[] args) {
        Integer[] nums = {3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root = TreeUtils.buildTree(nums);
        TreeUtils.printTree(root);
        TreeNode p = root.left.right.left;
        TreeNode q = root.left.right.right;
        T_236_BTAccesotor btAccesotor = new T_236_BTAccesotor();
        TreeNode lowestCommonAncestor = btAccesotor.lowestCommonAncestor(root, p, q);
        System.out.println(lowestCommonAncestor.val);
    }
}
