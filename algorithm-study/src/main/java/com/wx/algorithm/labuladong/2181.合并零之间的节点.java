/*
 * @lc app=leetcode.cn id=2181 lang=java
 * @lcpr version=30403
 *
 * [2181] 合并零之间的节点
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
    public ListNode mergeNodes(ListNode head) {
        // 直到遇到下一个0，之前的值都+起来
        // 删除0节点
        ListNode node = head.next;
        while(node != null) {
            ListNode nextNode = node.next;
            if (nextNode != null) {
                node.next = nextNode.next;
                if (nextNode.val == 0) {
                    node = node.next;
                } else {
                    node.val = node.val + nextNode.val;
                }
            }
        }
        
        return head.next;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [0,3,1,0,4,5,2,0]\n
// @lcpr case=end

// @lcpr case=start
// [0,1,0,3,0,2,2,0]\n
// @lcpr case=end

 */

