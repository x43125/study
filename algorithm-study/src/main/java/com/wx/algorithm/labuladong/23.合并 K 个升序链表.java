/*
 * @lc app=leetcode.cn id=23 lang=java
 * @lcpr version=30403
 *
 * [23] 合并 K 个升序链表
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
    public ListNode mergeKLists(ListNode[] lists) {
        // 两两排序，然后合并
        Deque<ListNode> queue = new LinkedList<>();
        for (ListNode listNode : lists) {
            queue.offer(listNode);
        }

        ListNode preNodeList = null, nowNodeList = null;
        while (!queue.isEmpty()) {
            if (preNodeList == null) {
                preNodeList = queue.pollFirst();
            } else {
                nowNodeList = queue.pollFirst();
                queue.offer(mergeTwoLists(preNodeList, nowNodeList));
                preNodeList = null;
            }
        }

        return preNodeList;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode node = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                node.next = list1;
                node = list1;
                list1 = list1.next;
            } else {
                node.next = list2;
                node = list2;
                list2 = list2.next;
            }
        }

        if (list1 != null) {
            node.next = list1;
        }
        if (list2 != null) {
            node.next = list2;
        }

        return dummy.next;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [[1,4,5],[1,3,4],[2,6]]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // []\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [[]]\n
 * // @lcpr case=end
 * 
 */
