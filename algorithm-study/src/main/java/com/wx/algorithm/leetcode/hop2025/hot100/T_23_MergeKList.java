package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.PriorityQueue;
import java.util.Queue;

import com.wx.algorithm.base.ListUtils;
import com.wx.algorithm.base.model.ListNode;

public class T_23_MergeKList {
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        int count = lists.length;
        for (int i = 0; i < count; i++) {
            if (lists[i] != null) {
                queue.offer(lists[i]);
            }
        }

        ListNode dummy = new ListNode();
        ListNode node = dummy;
        while (!queue.isEmpty()) {
            ListNode cur = queue.poll();
            ListNode temp = cur.next;
            node.next = cur;
            if (temp != null) {
                queue.offer(temp);
            }
            node = node.next;
        }

        return dummy.next;
    }

    public ListNode mergeKLists_02(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }

        if (l > r) {
            return null;
        }

        int mid = (l + r) >> 1;
        return merge2Lists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    private ListNode merge2Lists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }

        ListNode head = new ListNode();
        ListNode tail = head, aPtr = a, bPtr = b;
        while(aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }

        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }


    public static void main(String[] args) {
        int[] arr1 = {1,4,5};
        int[] arr2 = {1,3,4};
        int[] arr3 = {2,6};

        ListNode head1 = ListUtils.buildList(arr1);
        ListNode head2 = ListUtils.buildList(arr2);
        ListNode head3 = ListUtils.buildList(arr3);

        ListNode[] lists = new ListNode[]{head1, head2, head3};
        T_23_MergeKList mergeKList = new T_23_MergeKList();
        ListNode dummy = mergeKList.mergeKLists_02(lists);
        ListUtils.printList(dummy);
    }
}
