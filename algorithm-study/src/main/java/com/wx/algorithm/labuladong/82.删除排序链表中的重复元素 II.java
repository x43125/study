/*
 * @lc app=leetcode.cn id=82 lang=java
 * @lcpr version=30403
 *
 * [82] 删除排序链表中的重复元素 II
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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode headPreNode = new ListNode();
        headPreNode.next = head;
        ListNode preNode = headPreNode;
        ListNode node = head;
        while (node.next != null) {
            if (node.val != node.next.val) {
                if (preNode.next != node) {
                    preNode.next = node.next;
                } else {
                    preNode = preNode.next;
                }
            }
            node = node.next;
        }
        if (preNode.next != node) {
            preNode.next = node.next;
        }

        return headPreNode.next;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,2,3,3,4,4,5]\n
// @lcpr case=end

// @lcpr case=start
// [1,1,1,2,3]\n
// @lcpr case=end

 */

