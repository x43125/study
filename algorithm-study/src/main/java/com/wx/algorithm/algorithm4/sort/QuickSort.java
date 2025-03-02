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
        sort(a, low, j - 1);
        sort(a, j + 1, high);
    }

    private int partition(Comparable[] a, int low, int high) {
        // 因为下面用的是 ++i和--j，所以i起始从low开始，因为low本身就不需要比较，而j从high+1开始
        int i = low, j = high + 1;
        Comparable v = a[low];

        while (true) {
            while (i<high && less(a[++i], v)) {
            }
            while (j>low && less(v, a[--j])) {
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }

        exch(a, low, j);
        return j;
    }
}
