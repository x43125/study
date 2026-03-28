package com.wx.algorithm.leetcode.normal;

import java.util.HashMap;
import java.util.Map;

import com.wx.algorithm.base.model.TreeNode;
import com.wx.algorithm.base.TreeUtils;

public class T_337_Rob3 {

    // 假设选择某个节点，对应的最大值
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    // 假设不选择某个节点，对应的最大值
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();

    public int rob(TreeNode root) {
        // dfs(root);
        // return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));

        int[] res = dfs_02(root);
        return Math.max(res[0], res[1]);
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        // 选中当前节点：当前节点的值 + 不选中左子节点时候的最大值 + 不选中右子节点时候的最大值
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        // 不选当前节点：左子树的最大值 + 右子树的最大值   ->    max(选中左子节点的最大值， 不选中左子节点的最大值) + max(选中右子节点的最大值， 不选中右子节点的最大值)
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0))
                + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }

    // 优化，从上面的逻辑，可以发现，其实上一级只和左右子节点有关，和再后面的节点无关，所以没必要存下所有的值
    // 但每次递归都会产生两个值：选中和不选。所以要返回给前一级用的话，就需要一个数组来表示
    public int[] dfs_02(TreeNode node) {
        if (node == null) {
            // 到空节点，不管选中没选中，都没有值，所以返回0，0
            return new int[]{0, 0};
        }

        int[] left = dfs_02(node.left);
        int[] right = dfs_02(node.right);

        int[] cur = new int[2];
        cur[0] = node.val + left[1] + right[1];
        cur[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return cur;
    }

    public static void main(String[] args) {
        Integer[] num = new Integer[] { 3, 2, 3, null, 3, null, 1 };

        TreeNode root = TreeUtils.buildTree(num);
        T_337_Rob3 rob3 = new T_337_Rob3();
        int maxMoney = rob3.rob(root);
        System.out.println(maxMoney);
    }
}
