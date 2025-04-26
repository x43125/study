package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_295_Median {

    private Node head;
    private Node mid;
    private Double midVal;
    private int length;

    // 插入排序
    public T_295_Median() {
        head = new Node(Integer.MIN_VALUE);
        mid = head;
        length = 0;
        midVal = 0.0;
    }

    public void addNum(int num) {
        if (length == 0) {
            Node newNode = new Node(num);
            head.next = newNode;
            newNode.pre = head;
            mid = newNode;
            midVal = (double) mid.val;
            length++;
            return;
        }

        // 插入排序
        Node pre = head;
        Node cur = head.next;
        // 新插入的节点在mid右侧
        boolean midRight = false;
        while (cur != null) {
            if (pre == mid) {
                midRight = true;
            }
            if (cur.val < num) {
                pre = cur;
                cur = cur.next;
            } else {
                // 插入排序
                Node newNode = new Node(num);
                pre.next = newNode;
                newNode.pre = pre;
                newNode.next = cur;
                cur.pre = newNode;
                break;
            }
        }
        if (cur == null) {
            // 插入排序
            Node newNode = new Node(num);
            pre.next = newNode;
            newNode.pre = pre;
            if (pre == mid) {
                midRight = true;
            }
        }        

        length++;
        if (midRight) {
            if (length % 2 == 0) {
                // 如果是偶数，说明有两个中点
                // 1,3,5
                // mid = 3
                // 4 -> 1,3,4,5
                // 新插入的值在原mid右侧
                midVal = (mid.val + mid.next.val) / 2.0;
            } else {
                // 如果是奇数，说明有1个中点
                // 1,3,5,6
                // mid = 3
                // 4 -> 1,3,4,5,6
                // 新插入的值在原mid右侧
                mid = mid.next;
                midVal = (double) mid.val;
            }
        } else {
            // 新插入的值在原mid左侧
            if (length % 2 == 0) {
                // 如果是偶数，说明有两个中点
                // 1,3,5
                // mid = 3
                // 2 -> 1,2,3,5
                // 新插入的值在原mid左侧
                mid = mid.pre;
                midVal = (mid.val + mid.next.val) / 2.0;
            } else {
                // 如果是奇数，说明有1个中点
                // 那么就是这个中点
                // 1,3,4,5
                // mid = 3
                // 2 -> 1,2,3,4,5
                // 新插入的值在原mid右侧
                midVal = (double) mid.val;
            }
        }
    }

    public double findMedian() {
        return midVal;
    }

    static class Node {
        Integer val;
        Node next;
        Node pre;

        public Node(Integer val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        T_295_Median median = new T_295_Median();
        median.addNum(2);
        median.addNum(3);
        System.out.println(median.findMedian());
        median.addNum(4);
        System.out.println(median.findMedian());
        median.addNum(5);
        System.out.println(median.findMedian());
        median.addNum(6);
        System.out.println(median.findMedian());
        median.addNum(1);
        System.out.println(median.findMedian());
        median.addNum(0);
        System.out.println(median.findMedian());
    }
}
