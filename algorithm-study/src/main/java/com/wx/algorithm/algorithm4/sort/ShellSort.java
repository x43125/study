package com.wx.algorithm.algorithm4.sort;

/**
 * 希尔排序
 */
public class ShellSort extends SortBase {

    @Override
    public void sort(Comparable[] a) {
        // 三层循环：第一层控制排序间隔，第二、三层为插入排序的变种
        int n = a.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // 因为是靠j来做最终的比较插入的，所以是i不从0开始，从第二个数开始，
            for (int i = gap; i < n; i++) {
                // j从i开始，到gap即停止，每次向前推进gap个值
                for (int j = i; j >= gap && less(a[j], a[j - gap]); j -= gap) {
                    exch(a, j, j - gap);
                }
            }
        }
    }

}
