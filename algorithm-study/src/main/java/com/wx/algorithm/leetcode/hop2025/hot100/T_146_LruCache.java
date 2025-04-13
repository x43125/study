package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.HashMap;
import java.util.Map;

public class T_146_LruCache {
    static class DLinkNode {
        int key;
        int value;
        DLinkNode prev;
        DLinkNode next;

        public DLinkNode() {
        }

        public DLinkNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, DLinkNode> cache;
    private int size;
    private int capacity;
    private DLinkNode head, tail;

    public T_146_LruCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new DLinkNode();
        tail = new DLinkNode();
        head.next = tail;
        tail.prev = head;
        cache = new HashMap<>();
    }

    public int get(int key) {
        DLinkNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        move2head(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkNode oldNode = cache.get(key);
        if (oldNode == null) {
            DLinkNode node = new DLinkNode(key, value);
            cache.put(key, node);
            addNode(node);
            if (size == capacity) {
                DLinkNode tail = removeTail();
                cache.remove(tail.key);
                --size;
            }
            ++size;
        } else {
            oldNode.value = value;
            move2head(oldNode);
        }

    }

    private DLinkNode removeTail() {
        DLinkNode prev = tail.prev;
        removeNode(prev);
        return prev;
    }

    private void move2head(DLinkNode node) {
        removeNode(node);
        addNode(node);
    }

    private void addNode(DLinkNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
