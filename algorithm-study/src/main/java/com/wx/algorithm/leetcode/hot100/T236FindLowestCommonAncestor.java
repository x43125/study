package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.TreeNode;

/**
 * @author wangxiang
 * @date 2023/7/2 10:05
 * @description
 */
public class T236FindLowestCommonAncestor {
    /**
     * 两节点如果都在某节点的一侧的话，则说明不是
     * 两节点如果刚好分布在某节点两侧的话，则是
     * 两节点其中一节点在另一节点下，也是
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p) {
            if (traverse(root, q)) {
                return p;
            }
        } else if (root == q) {
            if (traverse(root, p)) {
                return q;
            }
        } else {
            if ((traverse(root.left, p) && traverse(root.right, q))
                    || (traverse(root.left, q) && traverse(root.right, p))) {
                return root;
            }
        }

        TreeNode node = lowestCommonAncestor(root.left, p, q);
        return node == null ? lowestCommonAncestor(root.right, p, q) : node;
    }

    private boolean traverse(TreeNode root, TreeNode node) {
        if (root == null) {
            return false;
        }
        if (root == node) {
            return true;
        }

        return traverse(root.left, node) || traverse(root.right, node);
    }

    public TreeNode lowestCommonAncestorOptimize(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestorOptimize(root.left, p, q);
        TreeNode right = lowestCommonAncestorOptimize(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else {
            return right;
        }
    }
}
