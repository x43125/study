package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.ListNode;

/**
 * @author wangxiang
 * @date 2023/6/27 15:14
 * @description
 */
public class T19RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 用于计数已经到nth了
        ListNode fast = head;
        // 用于计数当前就是nth
        ListNode node = head;
        // 用于计数nth.pre.pre用于删除操作
        ListNode preNode = new ListNode(-1);
        preNode.next = head;
        ListNode preHead = preNode;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            node = node.next;
            preNode = preNode.next;
        }
        preNode.next = node.next;

        return preHead.next;
    }
}
