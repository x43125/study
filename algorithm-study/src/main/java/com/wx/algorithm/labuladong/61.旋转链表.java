/*
 * @lc app=leetcode.cn id=61 lang=java
 * @lcpr version=30403
 *
 * [61] 旋转链表
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
    public ListNode rotateRight(ListNode head, int k) {
        // 像右旋转
        // 1。当k>长度时，求余
        // 2。找到倒数第 K 个数，将其设为新的头节点，并将其末尾接在原先的头结点
        if (head == null || k == 0) {
            return head;
        }
        int n = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            n++;
        }

        if (n == 1) {
            return head;
        }

        k = k % n;
        if (k == 0) {
            return head;
        }

        node = head;
        ListNode headPreNode = new ListNode();
        headPreNode.next = head;
        ListNode newHeadPreNode = headPreNode;

        ListNode tail = headPreNode;

        int i = 0;
        while (node != null) {
            if (i >= k) {
                newHeadPreNode = newHeadPreNode.next;
            }
            node = node.next;
            tail = tail.next;
            i++;
        }
        if (headPreNode != newHeadPreNode) {
            headPreNode.next = newHeadPreNode.next;
            tail.next = head;
            newHeadPreNode.next = null;
        }

        return headPreNode.next;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,2,3,4,5]\n2\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [0,1,2]\n4\n
 * // @lcpr case=end
 * 
 */
