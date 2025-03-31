package com.wx.algorithm.leetcode.hop2025.hot100;

import com.wx.algorithm.base.ListUtils;
import com.wx.algorithm.base.model.ListNode;

public class T_148_SortList {

    /**
     * 自顶向下归并排序
     * 
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    private ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }

        // 如果head的下一个是tail，说明不用再拆分了
        if (head.next == tail) {
            // 将每个节点拆散
            head.next = null;
            return head;
        }

        // 快慢指针，找中点
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }

        // 递归核心
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        // 因为上面处理的时候，没有算tail，所以此处可以用mid，而不用mid.next
        ListNode list2 = sortList(mid, tail);
        ListNode merged = merge(list1, list2);
        return merged;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy, temp1 = head1, temp2 = head2;

        while (temp1 != null || temp2 != null) {
            if (temp1 == null) {
                temp.next = temp2;
                break;
            }
            if (temp2 == null) {
                temp.next = temp1;
                break;
            }
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 2, 1, 3, 0 };
        ListNode list = ListUtils.buildList(arr);
        T_148_SortList sortList = new T_148_SortList();
        ListNode newHead = sortList.sortList(list);
        ListUtils.printList(newHead);
    }
}