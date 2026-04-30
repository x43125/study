/*
 * @lc app=leetcode.cn id=445 lang=java
 * @lcpr version=30403
 *
 * [445] 两数相加 II
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

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
        // 高位在左
        Deque<ListNode> stack1 = new ArrayDeque<>();
        Deque<ListNode> stack2 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }

        int reside = 0;
        ListNode next = null;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (!stack1.isEmpty() && !stack2.isEmpty()) {
                ListNode node1 = stack1.pop();
                ListNode node2 = stack2.pop();
                int sum = node1.val + node2.val + reside;
                int num = sum % 10;
                reside = sum / 10;
                node1.val = num;
                node1.next = next;
                next = node1;
            } else if (!stack1.isEmpty()) {
                // stack2 为空  
                ListNode node1 = stack1.pop();
                int sum = node1.val + reside;
                int num = sum % 10;
                reside = sum / 10;
                node1.val = num;
                node1.next = next;
                next = node1;
            } else {
                // stack1 为空  
                ListNode node2 = stack2.pop();
                int sum = node2.val + reside;
                int num = sum % 10;
                reside = sum / 10;
                node2.val = num;
                node2.next = next;
                next = node2;
            }
        }

        if (reside != 0) {
            ListNode head = new ListNode();
            head.val = reside;
            head.next = next;
            next = head;
        }
        return next;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [7,2,4,3]\n[5,6,4]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [2,4,3]\n[5,6,4]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [0]\n[0]\n
 * // @lcpr case=end
 * 
 */
