package com.wx.algorithm.algorithm4;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    public Item pop() {
        // 取得最后一条数据
        Item item = a[--N];
        // 避免数组数据游离占用内存
        a[N] = null;
        // 缩容
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        // 返回
        return item;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private void resize(int max) {
        // 生成一个新的数组
        Item[] temp = (Item[]) new Object[max];
        // 遍历将之前的数据复制过来
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        // 将新数组赋值给a
        a = temp;
    }

    /**
     * 迭代器
     */
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;
        
        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return a[--i];
        }

        public void remove() {
        }
    }
}