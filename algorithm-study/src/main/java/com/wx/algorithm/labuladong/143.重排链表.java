/*
 * @lc app=leetcode.cn id=143 lang=java
 * @lcpr version=30403
 *
 * [143] 重排链表
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
    public void reorderList(ListNode head) {
        // 1 2 3 4 5 6
        // 1 2 3 6 5 4
        // 1 6 2 5 3 4
        // 先将后半部分倒序，再快慢指针，分别去排
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = head;
        ListNode slow = dummy;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) {
            slow = slow.next;
        }

        // 倒排
        ListNode mid = slow;
        fast = mid.next;
        slow = null;
        while (fast != null) {
            ListNode next = fast.next;
            fast.next = slow;
            slow = fast;
            fast = next;
            mid.next = slow;
        }

        ListNode rHead = mid.next;
        mid.next = null;
        // 重新组合
        while (rHead != null) {
            ListNode node = rHead;
            rHead = rHead.next;
            ListNode headNext = head.next;
            node.next = headNext;
            head.next = node;
            head = headNext;
        }
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [1,2,3,4]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,2,3,4,5]\n
 * // @lcpr case=end
 * 
 */
