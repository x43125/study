/*
 * @lc app=leetcode.cn id=92 lang=java
 * @lcpr version=30403
 *
 * [92] 反转链表 II
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head.next == null || left == right) {
            return head;
        }
        ListNode headPreNode = new ListNode();
        headPreNode.next = head;
        ListNode node = head;
        ListNode segHeadPreNode = new ListNode();
        ListNode segTail = new ListNode();
        ListNode segNextNode = null;
        ListNode preNode = headPreNode;

        for (int i = 0; i < right; i++) {
            // left-1=1
            if (i < left-1) {
                preNode = node;
                node = node.next;
            } else if (i == left-1) {
                segHeadPreNode = preNode;
                segTail = node;
                ListNode next = node.next;
                node.next = segNextNode;
                segNextNode = node;
                node = next;
                // right = 4
            } else if (i < right) {
                segHeadPreNode.next = node;
                ListNode next = node.next;
                node.next = segNextNode;
                segNextNode = node;
                node = next;
                segTail.next = node;
                // right-1=3
            }
        }

        return headPreNode.next;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,2,3,4,5]\n2\n4\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [5]\n1\n1\n
 * // @lcpr case=end
 * 
 */
