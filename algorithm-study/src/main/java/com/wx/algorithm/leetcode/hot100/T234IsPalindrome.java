package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.ListNode;

/**
 * @author wangxiang
 * @date 2023/6/27 11:36
 * @description
 */
public class T234IsPalindrome {

    ListNode frontPointer;

    /**
     * 反向递归 + 正向遍历
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        frontPointer = head;
        return traverse(head);
    }

    private boolean traverse(ListNode node) {
        if (node != null) {
            // 继续向底层递归，从递归出来的时候，如果返回的是false，则直接向上继续返回。否则则比较当前值
            if (!traverse(node.next)) {
                return false;
            }
            // 之前的递归都返回的事true才能到这，开始比较当前值与前置值
            if (node.val != frontPointer.val) {
                return false;
            }
            // 本层值相等后 递归向前，前置值也响应向后一步
            frontPointer = frontPointer.next;
        }

        return true;
    }
}
