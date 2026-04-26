/*
 * @lc app=leetcode.cn id=24 lang=java
 * @lcpr version=30403
 *
 * [24] 两两交换链表中的节点
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
    public ListNode swapPairs(ListNode head) {
        ListNode headPreNode = new ListNode();
        headPreNode.next = head;
        ListNode preNode = headPreNode;

        ListNode node = head;
        while (node != null && node.next != null) {
            ListNode next = node.next;
            ListNode nextNext = next.next;
            next.next = node;
            preNode.next = next;
            preNode = node;
            node = nextNext;
        }

        preNode.next = node;        
        return headPreNode.next;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,2,3,4]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // []\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,2,3]\n
 * // @lcpr case=end
 * 
 */
