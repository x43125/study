/*
 * @lc app=leetcode.cn id=725 lang=java
 * @lcpr version=30403
 *
 * [725] 分隔链表
 */

// @lc code=start

import com.wx.algorithm.base.model.ListNode;

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
    public ListNode[] splitListToParts(ListNode head, int k) {
        // 只是把链表分成K个部分，前面部分的长度>=后面的部分，任意两部分的长度不能超过 1
        // 得到长度，然后先对K整除得到每个段的基本长度，再对K取余得到前面多少段需要挨个+1
        int n = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            n++;
        }

        int segs = n / k;
        int mod = n % k;

        ListNode[] ans = new ListNode[k];
        node = head;
        for (int i = 0; i < k; i++) {
            if (node == null) {
                break;
            }
            ans[i] = node;
            int seg = segs + (i < mod ? 1 : 0);
            while (seg > 1) {
                node = node.next;
                seg--;
            }
            ListNode temp = node;
            node = node.next;
            temp.next = null;
        }

        return ans;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,2,3]\n5\n
// @lcpr case=end

// @lcpr case=start
// [1,2,3,4,5,6,7,8,9,10]\n3\n
// @lcpr case=end

 */

