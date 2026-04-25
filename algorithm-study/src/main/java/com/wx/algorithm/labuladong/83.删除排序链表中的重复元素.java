/*
 * @lc app=leetcode.cn id=83 lang=java
 * @lcpr version=30403
 *
 * [83] 删除排序链表中的重复元素
 */

// @lc code=start

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode preNode = head, node = head.next;
        while (node != null) {
            if (preNode.val == node.val) {
                preNode.next = node.next;
            } else {
                preNode = preNode.next;
            }

            node = node.next;
        }

        return head;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,1,2]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,1,2,3,3]\n
 * // @lcpr case=end
 * 
 */
