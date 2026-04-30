/*
 * @lc app=leetcode.cn id=160 lang=java
 * @lcpr version=30403
 *
 * [160] 相交链表
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB;
        while ((nodeA != null || nodeB != null) && nodeA != nodeB) {
            if (nodeA == null) {
                nodeA = headB;
                nodeB = nodeB.next;
            } else if (nodeB == null) {
                nodeB = headA;
                nodeA = nodeA.next;
            } else {
                nodeA = nodeA.next;
                nodeB = nodeB.next;
            }
        }

        if (nodeA == nodeB) {
            return nodeA;
        }
        return null;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // 8\n[4,1,8,4,5]\n[5,6,1,8,4,5]\n2\n3\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // 2\n[1,9,1,2,4]\n[3,2,4]\n3\n1\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // 0\n[2,6,4]\n[1,5]\n3\n2\n
 * // @lcpr case=end
 * 
 */
