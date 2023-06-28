package com.wx.algorithm.leetcode.hot100;

import com.wx.algorithm.base.model.ListNode;

/**
 * @author wangxiang
 * @date 2023/6/28 07:53
 * @description
 */
public class T138CopyList {
    public static void main(String[] args) {
        
    }

    /**
     * 深拷贝：先将整个链表创建出来，再去处理random指针
     * @param head
     * @return
     */
    public RandomNode copyRandomList(RandomNode head) {
        RandomNode node = new RandomNode(head.val);
        RandomNode preHead = new RandomNode(-1);
        preHead.next = node;
        // 先将原先链表拷贝过来
        while(head!=null) {
            RandomNode next = new RandomNode(head.next.val);
            node.next = next;
            node = node.next;
            head = head.next;
        }
        // 再将random指针拷贝过来
        node = preHead.next;
        while(node != null) {
            
        }

    }
}
