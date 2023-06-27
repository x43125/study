package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.ListNode;

/**
 * @author wangxiang
 * @date 2023/6/27 15:51
 * @description
 */
public class T24SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        ListNode preNode = preHead, left = head, right = head.next;
        while (right != null) {
            ListNode temp = right.next;

            preNode.next = right;
            right.next = left;
            preNode = left;

            if (temp == null || temp.next == null) {
                preNode.next = temp;
                break;
            }
            left = temp;
            right = left.next;
        }

        return preHead.next;
    }
}
