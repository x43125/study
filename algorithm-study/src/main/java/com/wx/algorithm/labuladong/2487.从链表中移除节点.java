/*
 * @lc app=leetcode.cn id=2487 lang=java
 * @lcpr version=30403
 *
 * [2487] 从链表中移除节点
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
    public ListNode removeNodes(ListNode head) {
        // 暴力法：
        // 严格递减
        // 三指针，起始一个，起始前节点，node 节点
        // 每遇到一个更大的节点，都得从头开始跑，O(n^2)
        // ListNode headPreNode = new ListNode();
        // headPreNode.next = head;

        // ListNode node = head;
        // while (node.next != null) {
        //     if (node.val < node.next.val) {
        //         ListNode newHead = headPreNode;
        //         while (newHead.next.val >= node.next.val) {
        //             newHead = newHead.next;
        //         }
        //         newHead.next = node.next;
        //     }
        //     node = node.next;
        // }

        // return headPreNode.next;

        // 递归法：
        if (head == null) {
            return null;
        }
        ListNode newHead = removeNodes(head.next);
        if (newHead == null) {
            return head;
        }
        if (head.val >= newHead.val) {
            head.next = newHead;
            return head;
        } else {
            return newHead;
        }
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [5,2,13,3,8]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,1,1,1]\n
 * // @lcpr case=end
 * 
 */
