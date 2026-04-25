/*
 * @lc app=leetcode.cn id=147 lang=java
 * @lcpr version=30403
 *
 * [147] 对链表进行插入排序
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
    public ListNode insertionSortList(ListNode head) {
        ListNode headPreNode = new ListNode();
        headPreNode.next = head;
        ListNode node = head;
        while (node.next != null) {
            if (node.val > node.next.val) {
                ListNode temp = node.next;
                node.next = temp.next;
                ListNode newHead = headPreNode;
                while (newHead.next.val < temp.val) {
                    newHead = newHead.next;
                }
                temp.next = newHead.next;
                newHead.next = temp;
            } else {
                node = node.next;
            }
        }

        return headPreNode.next;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [4,2,1,3]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [-1,5,3,4,0]\n
 * // @lcpr case=end
 * 
 */
