/*
 * @lc app=leetcode.cn id=1290 lang=java
 * @lcpr version=30403
 *
 * [1290] 二进制链表转整数
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    int sum = 0;
    public int getDecimalValue(ListNode head) {
        // Integer.parseInt(binaryString, 2); 2 表示二进制
        dfs(head);
        return sum;
    }

    /**
     * 
     * @param node
     * @return level
     */
    private int dfs(ListNode node) {
        if (node == null) {
            return -1;
        }
        int level = dfs(node.next) + 1;
        sum += node.val * (1 << level);
        return level;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,0,1]\n
// @lcpr case=end

// @lcpr case=start
// [0]\n
// @lcpr case=end

 */

