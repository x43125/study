package com.wx.algorithm.labuladong;

public class SolutionTest {
    Integer secondMin;
    public int findSecondMinimumValue(TreeNode root) {
        // 根节点 == min(left, right)
        secondMin = null;
        dfs(root);
        
        return (secondMin == null || secondMin == root.val) ? -1 : secondMin;
    }

    private void dfs(TreeNode root) {
        if (root == null || root.left == null) {
            return;
        }

        if (root.left.val == root.right.val) {
            dfs(root.left);
            dfs(root.right);
        } else if (root.left.val < root.right.val) {
            secondMin = secondMin == null ? root.right.val : Math.min(root.right.val, secondMin);
            dfs(root.left);
        } else {
            secondMin = secondMin == null ? root.left.val : Math.min(root.left.val, secondMin);
            dfs(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root1 = TreeUtils.buildTree(new Integer[] { 2,2,2147483647 });

        SolutionTest solution = new SolutionTest(); 
        int ans = solution.findSecondMinimumValue(root1);
        System.out.println(ans);
    }
}
