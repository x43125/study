package com.wx.algorithm.leetcode.codetop;

import java.util.HashMap;
import java.util.Map;

public class T_146_LRUCache {

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode next;

        public DLinkedNode() {
        };

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // capacity
    // 当新的put导致容量超过capacity的时候，则删除最久未使用的数据
    // 每次调用一个数据就将其改为最新的一条数据
    // 删除，直接删除即可
    // 队列？get不是o1？堆？ hashmap + 双向链表 hashmap的value里直接存双向链表的node节点
    // hashmap存key-value，双向链表
    private int capacity;
    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private DLinkedNode head;
    private DLinkedNode tail;

    public T_146_LRUCache(int capacity) {
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        // 缓存存在key
        DLinkedNode node = cache.get(key);
        move2head(node);
        return node.value;
    }

    /**
     * 将链表中的节点移动到链首
     * 
     * @param node
     */
    private void move2head(DLinkedNode node) {
        // 将节点转移到头部
        removeNode(node);
        add2Head(node);
    }

    /*
     * 将节点加到链首
     */
    private void add2Head(DLinkedNode node) {
        node.next = head.next;
        head.next.pre = node;
        node.pre = head;
        head.next = node;
    }

    /**
     * 移除链表中的某节点
     * 
     * @param node
     */
    private void removeNode(DLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            DLinkedNode node = cache.get(key);
            node.value = value;
            move2head(node);
        } else {
            if (cache.size() == capacity) {
                cache.remove(tail.pre.key);
                removeNode(tail.pre);
            }
            DLinkedNode node = new DLinkedNode(key, value);
            add2Head(node);
            cache.put(key, node);
        }
    }

    public static void main(String[] args) {
        T_146_LRUCache lruCache = new T_146_LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
    }
}