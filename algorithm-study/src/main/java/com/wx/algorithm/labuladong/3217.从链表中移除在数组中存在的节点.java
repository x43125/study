/*
 * @lc app=leetcode.cn id=3217 lang=java
 * @lcpr version=30403
 *
 * [3217] 从链表中移除在数组中存在的节点
 */

// @lc code=start

import java.util.HashSet;
import java.util.Set;



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
    public ListNode modifiedList(int[] nums, ListNode head) {
        ListNode headPreNode = new ListNode();
        headPreNode.next = head;
        ListNode node = head, preNode = headPreNode;
        
        Set<Integer> set = new HashSet<>();
        for (Integer num : nums) {
            set.add(num);
        }

        while (node != null) {
            if (set.contains(node.val)) {
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
 * // @lcpr case=start
 * // [1,2,3]\n[1,2,3,4,5]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1]\n[1,2,1,2,1,2]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [5]\n[1,2,3,4]\n
 * // @lcpr case=end
 * 
 */
