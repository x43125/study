/*
 * @lc app=leetcode.cn id=25 lang=java
 * @lcpr version=30403
 *
 * [25] K 个一组翻转链表
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
    public ListNode reverseKGroup(ListNode head, int k) {
        // K个一组，每到K个，执行翻转
        // 不足K个的，不翻转：快慢指针，快指针先指向K个后，刚好也用来标志新头

        ListNode headPreNode = new ListNode();
        // 当前段的结尾节点
        ListNode kthNode = head;

        for (int i = 1; i < k; i++) {
            if (kthNode != null) {
                kthNode = kthNode.next;
            } else {
                return headPreNode.next;
            }
        }

        // 前一段儿的尾巴
        ListNode preSegTail = headPreNode;
        ListNode node = head;
        ListNode preNode = null;
        ListNode nowSegTail;

        while (kthNode != null) {
            nowSegTail = node;
            // 翻转当前K节点
            ListNode nextSegHeadNode = kthNode.next;
            while (node != nextSegHeadNode) {
                ListNode next = node.next;
                node.next = preNode;
                preNode = node;
                node = next;
            }
            preSegTail.next = kthNode;
            preSegTail = nowSegTail;
            kthNode = node;
            nowSegTail.next = node;


            // 将kthNode移动到下一个kth
            for (int i = 1; i < k; i++) {
                if (kthNode != null) {
                    kthNode = kthNode.next;
                } else {
                    return headPreNode.next;
                }
            }
        }

        return headPreNode.next;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,2,3,4,5]\n2\n
// @lcpr case=end

// @lcpr case=start
// [1,2,3,4,5]\n3\n
// @lcpr case=end

 */

