/*
 * @lc app=leetcode.cn id=1669 lang=java
 * @lcpr version=30403
 *
 * [1669] 合并两个链表
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
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode tail2 = list2;
        while (tail2.next != null) {
            tail2 = tail2.next;
        }

        ListNode head1 = list1;
        ListNode tail1 = list1;

        for (int i = 0; i < b; i++) {
            tail1 = tail1.next;
            if (i < a-1) {
                head1 = head1.next;
            } else if (i == a-1) {
                head1.next = list2;
            } else {

            }
        }
        tail2.next = tail1.next;

        return list1;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [10,1,13,6,9,5]\n3\n4\n[1000000,1000001,1000002]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [0,1,2,3,4,5,6]\n2\n5\n[1000000,1000001,1000002,1000003,1000004]\n
 * // @lcpr case=end
 * 
 */
