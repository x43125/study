package com.wx.algorithm.algorithm4.sort;

import java.util.Scanner;

/**
 * @author Shawn
 * @date 2025/2/28 09:04
 * @description
 */
public abstract class SortBase implements Sort {

    /**
     * 比较
     *
     * @param v
     * @param w
     * @return
     */
    public boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 交换
     *
     * @param a
     * @param i
     * @param j
     */
    public void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println();
    }

    public boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
