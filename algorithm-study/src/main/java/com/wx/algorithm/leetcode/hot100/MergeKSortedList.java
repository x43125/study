package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author wangxiang
 * @date 2023/6/29 15:04
 * @description
 */
public class MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        for (ListNode list : lists) {
            ans = merge2Lists(ans, list);
        }
        return ans;
    }

    public ListNode merge2Lists(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        ListNode preHead = new ListNode(-1);
        ListNode head = preHead;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                head.next = head1;
                head1 = head1.next;
            } else {
                head.next = head2;
                head2 = head2.next;
            }
            head = head.next;
        }

        head.next = head1 == null ? head2 : head1;
        return preHead.next;
    }

    /**
     * 归并思想合并：22组合
     * @param lists
     * @return
     */
    public ListNode mergeKListsOptimize(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }

        if (l > r) {
            return null;
        }

        int mid = (l + r) >> 1;
        return merge2Lists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    /**
     * 优先级队列优化
     *
     * @param lists
     * @return
     */
    public ListNode mergeKListsOptimize2(ListNode[] lists) {
        Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }
        ListNode preHead = new ListNode(-1);
        ListNode tail = preHead;
        while (!queue.isEmpty()) {
            ListNode cur = queue.poll();
            tail.next = cur;
            tail = tail.next;
            if (cur.next != null) {
                queue.offer(cur.next);
            }
        }
        return preHead.next;
    }

}
