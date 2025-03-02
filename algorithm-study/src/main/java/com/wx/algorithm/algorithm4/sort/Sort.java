package com.wx.algorithm.algorithm4.sort;

public interface Sort {
    void sort(Comparable[] a);

    boolean isSorted(Comparable[] a);

    void show(Comparable[] a);

    boolean less(Comparable i, Comparable j);

    void exch(Comparable[] a, int i, int j);
}
