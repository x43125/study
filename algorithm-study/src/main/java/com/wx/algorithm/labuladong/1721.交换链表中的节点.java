/*
 * @lc app=leetcode.cn id=1721 lang=java
 * @lcpr version=30403
 *
 * [1721] 交换链表中的节点
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
    public ListNode swapNodes(ListNode head, int k) {
        // 1.边界处理: 还有一种特殊情况，就是刚好这两个数相邻
        // 2.双指针
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode kthPreNode = dummy;
        ListNode lastKthPreNode = dummy;
        ListNode node = head;

        int i = 1;
        while (node.next != null) {
            if (i < k) {
                kthPreNode = kthPreNode.next;
            } else {
                lastKthPreNode = lastKthPreNode.next;
            }
            node = node.next;
            i++;
        }

        ListNode kthNode = kthPreNode.next;
        ListNode kthNextNode = kthNode.next;

        ListNode lastKthNode = lastKthPreNode.next;
        ListNode lastKthNextNode = lastKthNode.next;

        if (kthNextNode == lastKthNode) {
            kthPreNode.next = lastKthNode;
            lastKthNode.next = kthNode;
            kthNode.next = lastKthNextNode;
        } else if (lastKthNextNode == kthNode) {
            lastKthPreNode.next = kthNode;
            kthNode.next = lastKthNode;
            lastKthNode.next = kthNextNode;
        } else {
            kthPreNode.next = lastKthNode;
            lastKthNode.next = kthNextNode;

            lastKthPreNode.next = kthNode;
            kthNode.next = lastKthNextNode;
        }

        return dummy.next;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,2,3,4,5]\n2\n
// @lcpr case=end

// @lcpr case=start
// [7,9,6,6,7,8,3,0,9,5]\n5\n
// @lcpr case=end

 */

