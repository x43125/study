package com.wx.algorithm.algorithm4.sort;

/**
 * 插入排序
 */
public class InsertSort extends SortBase {

    @Override
    public void sort(Comparable[] a) {
        // 插入排序，从第二个开始一次向前面进行比较，找到它该呆的位置停止
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }
}
