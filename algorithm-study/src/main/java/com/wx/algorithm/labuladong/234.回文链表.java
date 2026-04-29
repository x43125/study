/*
 * @lc app=leetcode.cn id=234 lang=java
 * @lcpr version=30403
 *
 * [234] 回文链表
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
    public boolean isPalindrome(ListNode head) {
        // 从中间向两面走，每走一个，两边节点比较
        // 非双指针，无法直接比较，将中间点往后的链表翻转过来，再比较
        // 找到中间点
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = head;
        ListNode slow = dummy;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 0, 1,2,3,2,1
        // 0, 1,2,2,1
        if (fast != null) {
            slow = slow.next;
        }

        // 翻转，并拼接到原中间点之后
        ListNode mid = slow;
        // 开始翻转mid之后的数据
        fast = mid.next;
        slow = null;
        while (fast != null) {
            ListNode next = fast.next;
            fast.next = slow;
            slow = fast;
            fast = next;
            mid.next = slow;
        }

        // 比较
        while (slow != null) {
            if (slow.val != head.val) {
                return false;
            }
            head = head.next;
            slow = slow.next;
        }

        return true;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,2,2,1]\n
// @lcpr case=end

// @lcpr case=start
// [1,2]\n
// @lcpr case=end

 */

