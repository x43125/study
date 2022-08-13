package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.labuladong.list.pojo.ListNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/08/13
 */
public class Hot_02_SumTwo {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(9);
        ListNode l4 = new ListNode(9);
        ListNode l5 = new ListNode(9);
        ListNode l6 = new ListNode(9);
        l1.next = l2;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;

        Hot_02_SumTwo sumTwo = new Hot_02_SumTwo();
        ListNode node = sumTwo.addTwoNumbers(l1, l3);

        while (node != null) {
            System.out.print(node.val + " -> ");
            node = node.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean ten = false;
        ListNode l = new ListNode(0);
        ListNode head = l;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + (ten ? 1 : 0);
            ListNode node = new ListNode(0);
            if (sum > 9) {
                ten = true;
                node.val = sum % 10;
            } else {
                node.val = sum;
                ten = false;
            }

            l1 = l1.next;
            l2 = l2.next;
            l.next = node;
            l = l.next;
        }

        while (l1 != null) {
            if (ten) {
                if (l1.val == 9) {
                    l1.val = 0;
                } else {
                    l1.val++;
                    ten = false;
                }
            }
            l.next = l1;
            l = l.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            if (ten) {
                if (l2.val == 9) {
                    l2.val = 0;
                } else {
                    l2.val++;
                    ten = false;
                }
            }
            l.next = l2;
            l = l.next;
            l2 = l2.next;
        }
        if (ten) {
            l.next = new ListNode(1);
        }

        return head.next;
    }
}
