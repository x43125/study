/*
 * @lc app=leetcode.cn id=817 lang=java
 * @lcpr version=30403
 *
 * [817] 链表组件
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
    public int numComponents(ListNode head, int[] nums) {
        // 从头开始遍历，只要链表里属于 nums 中的，就next,不在的时候，就+1，
        // 每次在的就把nums中的数据 remove 掉
        int cnt = 0;
        boolean has = false;

        Set<Integer> set = new HashSet<>();
        for (Integer num : nums) {
            set.add(num);
        }

        while (head != null) {
            if (set.contains(head.val)) {
                if (!has) {
                    has = true;
                    cnt++;
                }
            } else {
                if (has) {
                    has = false;
                }
            }
            head = head.next;
        }

        return cnt;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [0,1,2,3]\n[0,1,3]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [0,1,2,3,4]\n[0,3,1,4]\n
 * // @lcpr case=end
 * 
 */
