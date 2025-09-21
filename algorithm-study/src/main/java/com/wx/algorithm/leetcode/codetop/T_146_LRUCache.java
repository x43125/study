package com.wx.algorithm.leetcode.codetop;

import java.util.HashMap;
import java.util.Map;

public class T_146_LRUCache {
    // 双向链表来表示最近和最远
    // 每次查看某个节点，就将这个节点调整到最前面
    // 每次新增的节点，也插到最前面
    //  如果超过长度，则将最后一位删除掉
    // 要达到O(1)的访问速度，必须使用hash

    // 双向链表
    class Node {
        Integer key;
        Integer val;
        Node next;
        Node pre;

        public Node(){}
        public Node(Integer key, Integer val) {
            this.key = key;
            this.val = val;
        }
    }

    // map
    private Map<Integer, Node> map;
    // 双向队列
    private Node head;
    private Node tail;
    // 长度
    int capacity;
    
    public T_146_LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;

        map = new HashMap<>();
    }
    
    public int get(int key) {
        // 通过查询Map快速获取值
        // 将该节点提前到队首
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        move2head(node);
        return node.val;
    }
    
    private void move2head(Node node) {
        delNode(node);
        add2head(node);
    }

    private void delNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void add2head(Node node) {
        head.next.pre = node;
        node.next = head.next;
        head.next = node;
        node.pre = head;
    }

    public void put(int key, int value) {
        // 如果已经存在，则移到队首
        // 如果未存在
        //   如果队列未满则直接插到队首
        //   如果已满，则先删除最后一位，再插入队首
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            move2head(node);
            map.put(key, node);
        } else {
            if (map.size() >= capacity){
                map.remove(tail.pre.key);
                delNode(tail.pre);
            }
            Node node = new Node(key, value);
            add2head(node);
            map.put(key, node);
        }
    }

    public static void main(String[] args) {
        T_146_LRUCache lruCache = new T_146_LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}