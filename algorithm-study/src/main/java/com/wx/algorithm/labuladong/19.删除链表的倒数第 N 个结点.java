/*
 * @lc app=leetcode.cn id=19 lang=java
 * @lcpr version=30403
 *
 * [19] 删除链表的倒数第 N 个结点
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode headPreNode = new ListNode();
        headPreNode.next = head;
        ListNode nthNode = head;
        ListNode targetNode = headPreNode;
        int i = 0;
        while (nthNode != null) {
            if (i >= n) {
                targetNode = targetNode.next;
            }
            nthNode = nthNode.next;
            i++;
        }

        targetNode.next = targetNode.next.next;
        return headPreNode.next;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,2,3,4,5]\n2\n
// @lcpr case=end

// @lcpr case=start
// [1]\n1\n
// @lcpr case=end

// @lcpr case=start
// [1,2]\n1\n
// @lcpr case=end

 */

