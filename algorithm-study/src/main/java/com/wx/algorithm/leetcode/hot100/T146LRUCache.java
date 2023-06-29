package com.wx.algorithm.leetcode.hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * Hash + Map
 *
 * @author wangxiang
 * @date 2023/6/29 16:05
 * @description
 */
public class T146LRUCache {
    /**
     * 双向链表
     */
    class DLinkedList {
        int key;
        int value;
        DLinkedList next;
        DLinkedList prev;

        public DLinkedList() {
        }

        public DLinkedList(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 头节点前一个节点
     */
    DLinkedList preHead = new DLinkedList();
    /**
     * 尾节点后一个节点
     */
    DLinkedList afterTail = new DLinkedList();

    /**
     * 直接将双向链表存储起来
     */
    Map<Integer, DLinkedList> map;
    int capacity;

    /**
     * LRU 容量,当超过的时候，剔除出最久未使用的值
     *
     * @param capacity
     */
    public T146LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity + 1);
        preHead.next = afterTail;
        afterTail.prev = preHead;
    }

    /**
     * 获取值：
     * 如果有值则返回并将该值移到链首
     * 如果没有值则返回 -1
     *
     * @param key
     * @return
     */
    public int get(int key) {
        if (map.containsKey(key)) {
            DLinkedList node = map.get(key);
            move2Head(node);
            return node.value;
        }
        return -1;
    }

    /**
     * 插入值：
     * 如果已经存在，则更新，并将该值移到链首
     * 如果不存在，则插入，并在链首插一个值
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DLinkedList node = map.get(key);
            node.value = value;
            move2Head(node);
        } else {
            DLinkedList node = new DLinkedList(key, value);
            add2Head(node);
            map.put(key, node);
        }
        if (exceedCapacity()) {
            removeTail();
        }
    }

    private boolean exceedCapacity() {
        return map.size() > capacity;
    }

    private void move2Head(DLinkedList node) {
        // 重新配置原先位置的关系
        node.next.prev = node.prev;
        node.prev.next = node.next;
        // 新位置的 关系
        node.next = preHead.next;
        preHead.next.prev = node;
        node.prev = preHead;
        preHead.next = node;
    }

    private void removeTail() {
        map.remove(afterTail.prev.key);
        afterTail.prev.prev.next = afterTail;
        afterTail.prev = afterTail.prev.prev;
    }

    private void add2Head(DLinkedList node) {
        preHead.next.prev = node;
        node.next = preHead.next;
        node.prev = preHead;
        preHead.next = node;
    }
}
