/*
 * @lc app=leetcode.cn id=2058 lang=java
 * @lcpr version=30403
 *
 * [2058] 找出临界点之间的最小和最大距离
 */

// @lc code=start
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
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        // 2、记录第一个点、最后一个点的位置
        // 3、记录每相邻的两个点的差值

        ListNode pre = head, cur = head.next;
        int firstIndex = -1;
        int preIndex = -1;
        int index = 0;
        int[] ans = {-1, -1};
        while (cur != null) {
            if (cur.next != null) {
                // 1、判断是不是临界点
                if ((pre.val < cur.val && cur.next.val < cur.val) || (pre.val > cur.val && cur.next.val > cur.val)) {
                    if (firstIndex == -1) {
                        firstIndex = index;
                    }

                    if (preIndex != -1) {
                        ans[0] = ans[0] == -1 ? (index - preIndex) : Math.min(ans[0], index - preIndex);
                    }

                    preIndex = index;
                }
            }
            cur = cur.next;
            pre = pre.next;
            index++;
        }

        ans[1] = firstIndex == preIndex ? -1 : (preIndex - firstIndex);
        return ans;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [3,1]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [5,3,1,2,5,1,2]\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1,3,2,2,3,2,2,2,7]\n
 * // @lcpr case=end
 * 
 */
