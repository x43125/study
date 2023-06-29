package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.ListNode;
import com.wx.algorithm.base.model.RandomNode;

/**
 * @author wangxiang
 * @date 2023/6/28 07:53
 * @description
 */
public class T138CopyList {
    public static void main(String[] args) {

    }

    /**
     * 先在原先的基础上添加一个拷贝，然后再分离出来，妙
     *
     * @param head
     * @return
     */
    public RandomNode copyRandomList(RandomNode head) {
        if (head == null) {
            return null;
        }
        // 1. 拷贝原节点，生成新的链： A -> B -> C  ==> A -> A' -> B -> B' -> C -> C'
        // 因为会加一个拷贝节点进来，所以每次是node=node.next.next;
        for (RandomNode node = head; node != null; node = node.next.next) {
            // 拷贝节点
            RandomNode nodeNew = new RandomNode(node.val);
            // 拷贝节点的.next=node.next
            nodeNew.next = node.next;
            // node.next=拷贝节点，这样就将拷贝节点加到原先的两节点之间了
            node.next = nodeNew;
        }
        // 2. 拷贝原节点的Random指针
        for (RandomNode node = head; node != null; node = node.next.next) {
            // 获取拷贝节点
            RandomNode randomNode = node.next;
            // 拷贝节点的random指针应该是原节点的random指针的拷贝节点，也就是 node.random.next
            // 而每一个节点的random指针可能为null，如果直接 node.random.next 可能会空指针
            randomNode.random = node.random == null ? null : node.random.next;
        }
        // 3. 将原链分离出来
        RandomNode headNew = head.next;
        for (RandomNode node = head; node != null; node = node.next) {
            RandomNode nodeNew = node.next;
            // 将拷贝节点从原链中剥离出来
            node.next = node.next.next;
            // 拷贝节点的next==原节点的.next.next，也就是此处的nodeNew.next
            nodeNew.next = nodeNew.next == null ? null : nodeNew.next.next;
        }

        return headNew;
    }
}
