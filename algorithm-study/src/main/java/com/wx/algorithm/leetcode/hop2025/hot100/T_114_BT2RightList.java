package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.TreeUtils;
import com.wx.algorithm.base.model.TreeNode;

public class T_114_BT2RightList {
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while(cur != null) {
            // 一层一层处理
            // 如果左边不为空的话
            if (cur.left != null) {
                // 将右子树，移动到左子树最右下方
                TreeNode left = cur.left;
                TreeNode pre = left;
                // 为了到左子树最右下方，while循环
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 将右子树移动到左子树最右下方
                pre.right = cur.right;
                // 然后将左子树移动到右子树上
                cur.left = null;
                cur.right = left;
            }
            // 再去下一层，用同样的方法来处理，有点正向递归的感觉
            cur = cur.right;
        }
    }

    public static void main(String[] args) {
        Integer[] nums = {1,2,5,3,4,null,6};
        TreeNode root = TreeUtils.buildTree(nums);
        T_114_BT2RightList bt2RightList = new T_114_BT2RightList();
        bt2RightList.flatten(root);
        TreeUtils.printTree(root);
    }
}
