/*
 * @lc app=leetcode.cn id=206 lang=java
 * @lcpr version=30403
 *
 * [206] 反转链表
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
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode headPrNode = new ListNode();
        headPrNode.next = head;
        ListNode node = head;

        dfs(headPrNode, node);
        head.next = null;
        return headPrNode.next;
    }

    private ListNode dfs(ListNode headPrNode, ListNode node) {
        if (node == null) {
            return null;
        }

        ListNode newPreNode = dfs(headPrNode, node.next);

        if (newPreNode == null) {
            headPrNode.next = node;
        } else {
            newPreNode.next = node;
        }

        return node;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,2,3,4,5]\n
// @lcpr case=end

// @lcpr case=start
// [1,2]\n
// @lcpr case=end

// @lcpr case=start
// []\n
// @lcpr case=end

 */

