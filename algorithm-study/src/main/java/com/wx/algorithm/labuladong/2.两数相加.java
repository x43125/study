/*
 * @lc app=leetcode.cn id=2 lang=java
 * @lcpr version=30403
 *
 * [2] 两数相加
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        dummy.next = l1;
        ListNode preNode = dummy;
        int preReside = 0;
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                int sum = preReside + l1.val + l2.val;
                int num = sum % 10;
                preReside = sum / 10;
                l1.val = num;
                preNode = l1;
                l1 = l1.next;
                l2 = l2.next;
            } else if (l1 != null) {
                if (preReside == 0) {
                    return dummy.next;
                }
                int sum = preReside + l1.val;
                int num = sum % 10;
                preReside = sum / 10;
                l1.val = num;
                preNode = l1;
                l1 = l1.next;
            } else {
                if (preReside == 0 && preNode.next == l2) {
                    return dummy.next;
                }
                int sum = preReside + l2.val;
                int num = sum % 10;
                preReside = sum / 10;
                l2.val = num;
                if (preNode.next != l2) {
                    preNode.next = l2;
                }
                preNode = l2;
                l2 = l2.next;
            }
        }
        if (preReside != 0) {
            ListNode tail = new ListNode();
            tail.val = preReside;
            preNode.next = tail;
        }

        return dummy.next;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [2,4,3]\n[5,6,4]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [0]\n[0]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [9,9,9,9,9,9,9]\n[9,9,9,9]\n
 * // @lcpr case=end
 * 
 */
