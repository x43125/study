package com.wx.algorithm.algorithm4.sort;

public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int n = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(Key v) {
        pq[++n] = v;
        swim(n);
    }

    public Key delMax() {
        Key max = pq[1];
        // 将最后一位的数与第一位交换
        exch(1, n--);
        // 将最后一位置空
        pq[n + 1] = null;
        // 将第一位数沉降到目标位置
        sink(1);
        return max;
    }

    // 上浮和插入值有关
    // 会上浮到目标位置
    private void swim(int k) {
        // 因为在数组中根节点在第1位，所以k要>1
        // 如果子节点小于根节点，则交换两个节点
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    /**
     * 下沉则和删除最大值有关
     * @param k
     */
    private void sink(int k) {
        // 如果有子节点的话
        while (2 * k <= n) {
            // 计算子节点
            int j = 2 * k;
            // 如果子节点存在，且有右子节点，则比较左右节点谁更大，来决定替换谁
            if (j < n && less(j, j+1)) {
                j++;
            }
            // 如果>=更大的节点的话，则不需要再比较了，此位置就是它应该待的位置
            if (!less(k, j)) {
                break;
            }
            // 否则和两节点中更大的那个替换位置，然后进入下一轮比较
            exch(k, j);
            k = j;
        };
    }

    // public void sort(Comparable[] a) {
    //     int n = a.length;
    //     for (int k = n/2; k>=1; k--) {
    //         sink(a, k, n);
    //     }

    //     while (n > 1) {
    //         exch(a, 1, n--);
    //         sink(a, 1, n);
    //     }
    // }
}
