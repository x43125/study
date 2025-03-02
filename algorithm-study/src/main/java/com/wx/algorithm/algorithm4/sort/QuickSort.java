package com.wx.algorithm.algorithm4.sort;

/**
 * 快排
 */
public class QuickSort extends SortBase {
    @Override
    public void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int low, int high) {
        if (high <= low) {
            return;
        }
        int j = partition(a, low, high);
        sort(a, low, j-1);
        sort(a, j+1, high);
    }

    /**
     * 既分组又排序
     * @param a
     * @param low
     * @param high
     * @return
     */
    private int partition(Comparable[] a, int low, int high) {
        int i = low, j = high + 1;
        Comparable v = a[low];
        while (true) {
            while (less(a[++i], v) && i < high);
            while (less(v, a[--j]) && j > low);
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, low, j);
        return j;
    }
}
