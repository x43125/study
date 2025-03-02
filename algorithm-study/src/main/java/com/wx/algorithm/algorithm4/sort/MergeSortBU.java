package com.wx.algorithm.algorithm4.sort;

public class MergeSortBU extends MergeSort {

    /**
     * 自底向上归并
     */
    @Override
    public void sort(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[n];
        for (int i = 1; i < n; i *= 2) {
            for (int j = 0; j < n - i; j += 2 * i) {
                merge(a, j, j + i - 1, Math.min(j + 2 * i - 1, n - 1));
            }
        }
    }
}
