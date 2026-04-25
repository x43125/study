/*
 * @lc app=leetcode.cn id=2807 lang=java
 * @lcpr version=30403
 *
 * [2807] 在链表中插入最大公约数
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
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode node = head;
        while (node.next != null) {
            ListNode midNode = new ListNode(midNum(node.val, node.next.val));
            midNode.next = node.next;
            node.next = midNode;
            node = midNode.next;
        }

        return head;
    }

    private int midNum(int val, int val2) {
        // 最大公约数
        // 暴力法：
        // int min = Math.min(val, val2);
        // for (int i = min; i > 0; i--) {
        //     if (val % i == 0 && val2 % i == 0) {
        //         return i;
        //     }
        // }
        // return 1;

        // 公式法：
        while (val2 != 0) {
            int temp = val % val2;
            val = val2;
            val2 = temp;
        }

        return val;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [18,6,10,3]\n
// @lcpr case=end

// @lcpr case=start
// [7]\n
// @lcpr case=end

 */

