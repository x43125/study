/*
 * @lc app=leetcode.cn id=203 lang=java
 * @lcpr version=30403
 *
 * [203] 移除链表元素
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
    public ListNode removeElements(ListNode head, int val) {
        ListNode headPreNode = new ListNode();
        headPreNode.next = head;
        ListNode node = head;
        ListNode preNode = headPreNode;
        while (node != null) {
            if (node.val == val) {
                preNode.next = node.next;
            } else {
                preNode = preNode.next;
            }

            node = node.next;
        }

        return headPreNode.next;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,2,6,3,4,5,6]\n6\n
// @lcpr case=end

// @lcpr case=start
// []\n1\n
// @lcpr case=end

// @lcpr case=start
// [7,7,7,7]\n7\n
// @lcpr case=end

 */

